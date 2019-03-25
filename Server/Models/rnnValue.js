var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var RnnValue = new Schema({
    value:{type:String}
});

var RnnValue = mongoose.model('rnnValue',RnnValue);
module.exports = RnnValue;