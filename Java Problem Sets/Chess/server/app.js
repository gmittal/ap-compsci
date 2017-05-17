/* Really really hacky server --- great for small stuff */

var bodyParser = require('body-parser')
var express = require('express')
var exec = require('child_process').exec
var fs = require('fs')

var app = express()
app.use(bodyParser())

var C_PORT = 1134

app.get('/new/', function (req, res) {
  fs.writeFileSync(C_PORT+'.json', '{"games": {"12348": []}}');
  exec('json-server ' + C_PORT +'.json -p '+C_PORT+' -q &');
  C_PORT++
  res.send((C_PORT-1).toString());
})

app.listen(9000, function () {
  console.log('Chess server listening on port 9000.')
})
