import Cors from 'cors'
 import initMiddleware from '../../lib/init-middleware'

 // Initialize the cors middleware
 const cors = initMiddleware(
   // You can read more about the available options here: https://github.com/expressjs/cors#configuration-options
   Cors({
     // Only allow requests with GET, POST and OPTIONS
    methods: ['GET', 'POST', 'OPTIONS'],
   })
 )

 export default async function handler(req, res) {
   // Run cors
   await cors(req, res)

   // Rest of the API logic
   res.json({ message: 'Hello Everyone!' })
 }


// var express = require('express')
// var cors = require('cors')
// var app = express()

// var corsOptions = {
//   origin: 'http://localhost:8080',
//   optionsSuccessStatus: 200 // some legacy browsers (IE11, various SmartTVs) choke on 204
// }

// app.get('/library/list', cors(corsOptions), function (req, res, next) {
//   return res;
//   //res.json({msg: 'This is CORS-enabled for only example.com.'})
// })

// app.listen(8080, function () {
//   console.log('CORS-enabled web server listening on port 80')
// })