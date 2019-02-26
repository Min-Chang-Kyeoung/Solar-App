var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var PanelSchema = new Schema({
    name: {type:String},
    company:{type:String},
    type:{type:String},
    percent:{type:String},
    range:{type:String},
    weight:{type:String},
    appearance:{type:String},
    imgUrl:{type:String},
    price:{type:String}
});

var Panel = mongoose.model('Panel',PanelSchema);
module.exports = Panel;