/*
  APPUNTI SUL CODICE:
  - la keyword await stoppa l'esecuzione fino a quando la promise della funzione async che segue await non viene soddisfatta;
  - i dati "compleanno" sui clienti sono salvati nel db nel formato (stringa) dd/mm, mentre quelli "scadenza" sui buoni sconto sono nel formato Date;
  - i dati "codice" dei buoni sconto sono interi a 6 cifre e sono generati randomicamente;
  - il forEach sugli array non riuscivo a farlo funzionare per un tema di funzioni sync/async quindi ho lasciato perdere;
*/

const MongoClient = require("mongodb").MongoClient; // driver MongoDB
const MONGODB_URI = "mongodb+srv://user:colognoli@elaborato.vmaji.mongodb.net/db?retryWrites=true&w=majority"; // URI database MongoDB del progetto


async function connectToDatabase() {
  const client = await MongoClient.connect(MONGODB_URI);
  return await client.db("db");
}


exports.handler = async (event, context) => {
  context.callbackWaitsForEmptyEventLoop = false; // fa il return senza aspettare che il runtime event loop (non so cosa sia) sia vuoto - il tutorial di MongoDB la metteva dunque la lascio
  
  
  const db = await connectToDatabase();
  const clienti = await db.collection("clienti");
  const buoniSconto = await db.collection("buoniSconto");
  
  // ELIMINAZIONE BUONI SCONTO SCADUTI
  let today = new Date();
  let today_mmdd = ("0" + today.getDate()).slice(-2) + "/" + ("0" + (today.getMonth() + 1)).slice(-2); // getMonth() fornisce gennaio come 0
 
  await buoniSconto.deleteMany({
                      scadenza: { $lte: today }
                    });
  
  
  // GENERAZIONE BUONI SCONTO
  // scarica clienti che compiono gli anni oggi
  const festeggiati = await clienti.find({
                                      compleanno: today_mmdd
                                    }).toArray();
  
  
  // generazione buono sconto per festeggiati (con rigenerazione in caso esista già uno sconto col codice scelto inizialmente)
  for(let i = 0; i < festeggiati.length; i++) {
    let codicePresente = true;
    while(codicePresente) {
      let nuovoCodice = Math.floor(Math.random() * 1000000);
      let nuovaScadenza = new Date(today.getFullYear(), (today.getMonth() + 2) % 12, today.getDate());
    
      // per la verifica di unicità del codice generato, faccio una scarica dal db ogni volta per non dover tenere aggiornata una copia locale dello stesso ad ogni inserimento (tanto non ci sono problemi di velocità di esecuzione)
      const codiceDuplicato = await buoniSconto.find({
                                                  codice: nuovoCodice
                                                }).toArray().length > 0;
      if(!codiceDuplicato) {
        await buoniSconto.insertOne({
                            codice: nuovoCodice,
                            scadenza: nuovaScadenza,
                            titolare: festeggiati[i]["_id"] 
                          });
      
        codicePresente = false;
      }
    }    
  }
};