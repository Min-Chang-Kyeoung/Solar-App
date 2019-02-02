var User = require('../Models/User');

module.exports = {

    insertUser : function (req,res) {

        let newUser = new User({
            name:req.body.name,
            id:req.body.id,
            password:req.body.password,
            generator_num:req.body.generator_num
        }).then(
           res.send({message:'new user inserted'})
        ).catch(
            res.send(error)
        );
    },

    loginUser : function(req,res) {

    }

}