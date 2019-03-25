const RnnValue = require("../Models/rnnValue")

module.exports = {
    getRnnData : function(req,res){
        let rnnValue = RnnValue.find()
        rnnValue.exec()
        .then(rnnValue =>{res.send(rnnValue); console.log(rnnValue);
        } )
        .catch(err => {res.send(err)})
    },
}