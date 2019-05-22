var express        = require('express');
var app            = express();
var bodyParser     = require('body-parser');
var methodOverride = require('method-override');
var logger         = require('morgan');
var port = process.env.PORT || 3001; 


app.use(logger('tiny'));
app.use(bodyParser.json());
app.use(bodyParser.json({ type: 'application/vnd.api+json' })); 
app.use(bodyParser.urlencoded({ extended: true })); 
app.use(methodOverride('X-HTTP-Method-Override'));

var server = app.listen(port);
server.timeout = 5000;
var io = require('socket.io')(server);
app.set('socketio',io);

app.get('/test',(req,res)=>{
    res.send("8.7");
})

console.log('server is on port ' + port);
exports = module.exports = app;
