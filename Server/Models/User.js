var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var UserSchema = new Schema({
    name: {type:String},
    id:{type:String,'default':0},
    password:{type:String},
    generator_num:{type:String},
});

var User = mongoose.model('User',UserSchema);
module.exports = User;