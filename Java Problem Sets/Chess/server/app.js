/* Really really hacky server --- great for small stuff */

var express = require('express')
var bodyParser = require('body-parser')
var fs = require('fs')

var app = express()
app.use(bodyParser.json({limit: '50mb', extended: true}))
app.use(bodyParser.urlencoded({limit: '50mb', extended: true}))
app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*")
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")
  next()
})

var C_PORT = 1134

app.get('/new/', function (req, res) {
  fs.writeFileSync('./data/'+C_PORT+'.json', '{"games": {"'+C_PORT+'": []}}')
  C_PORT++
  res.send((C_PORT-1).toString())
})

app.post('/:PORT/games', function (req, res) {
  fs.writeFileSync('./data/'+req.params.PORT+'.json', JSON.stringify(req.body))
  res.sendStatus(200)
})

app.get('/:PORT/games', function (req, res) {
  var found = false
  fs.readdirSync('./data').forEach(file => {
    if ((req.params.PORT).toString() + '.json' === file)
      found = true
  })

  found ? res.send(fs.readFileSync('./data/'+req.params.PORT+'.json', 'utf-8')) : res.sendStatus(404)
})

app.listen(9000, function () {
  console.log('AP CS Chess Server listening on port 9000.')
})
