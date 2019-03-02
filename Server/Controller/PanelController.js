const Panel = require("../Models/Panels")

module.exports = {
    getAllPanelData : function(req,res){
        let panelData = Panel.find()
        panelData.exec()
        .then(panels =>{res.send(panels)})
        .catch(err => {res.send(err)})
    },

    postDetailPanelData : function(req,res){
        let _id = req.body._id
        let panelData = Panel.findById({_id:_id})
        panelData.exec()
        .then(panel => {res.send(panel)})
        .catch(err => {res.send(err)})
    }
}