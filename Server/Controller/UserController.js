const User = require('../Models/User');
const jwt = require('jsonwebtoken');

module.exports = {

    insertUser : function (req,res) {

        let newUser = new User({
            name:req.body.name,
            id:req.body.id,
            password:req.body.password,
            generator_num:req.body.generator_num
        });
        newUser.save()
        .then(user => {
            console.log(user);
            res.send({masseage:'success'});
        })
        .catch(err => {
            console.log(err);
            res.send({err:err})
        })
    },

    loginUser : function(req,res) {
    let id = req.decoded.id;

    let onError = (error) => {
        res.status(403).json({
            message: error.message
        })
    }

    User.findOne({id:id}).exec()
        .then(user => {
            res.send(user);
        })
        .catch(onError)
    }

}