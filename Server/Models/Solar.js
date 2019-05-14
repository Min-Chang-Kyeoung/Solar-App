var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var Solar = new Schema({
    value:{type:String},
    date:{type:String}
});

var Solar = mongoose.model('Solar',Solar);
module.exports = Solar;