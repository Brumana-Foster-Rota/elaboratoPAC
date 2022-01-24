const MongoClient = require("mongodb").MongoClient; // driver MongoDB
const MONGODB_URI = "mongodb+srv://user:colognoli@elaborato.vmaji.mongodb.net/db?retryWrites=true&w=majority"; // URL cluster MongoDB dell'elaborato
let cached_db = null;


async function connectToDatabase() {
  if (cached_db) {
    return cached_db;
  }

  const client = await MongoClient.connect(MONGODB_URI);
  const db = await client.db("db");
  cached_db = db;
  
  return db;
}


exports.handler = async (event, context) => {
  context.callbackWaitsForEmptyEventLoop = false; // fa il return senza aspettare che il runtime event loop (non so cosa sia) sia vuoto - il tutorial di MongoDB la metteva dunque la lascio
  
  let today = new Date();
  let todayFilter = ("0" + today.getDate()).slice(-2) + "/" + ("0" + (today.getMonth() + 1)).slice(-2); // getMonth() fornisce gennaio come 0
 
  
  const db = await connectToDatabase();
  
  await db.collection("buoniSconto").deleteMany({scadenza:{$lte: today}});
  const buoniSconto = db.collection("buoniSconto").find({}).toArray();
  
  const clienti = await db.collection("clienti").find({compleanno: todayFilter}).toArray();
  
  
  const response = {
    buoniSconto: JSON.stringify(buoniSconto),
    clienti: JSON.stringify(clienti)
  };

  return response;
};