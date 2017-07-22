const express = require('express');
const router = express.Router();

const connection = require('../mysql.js');
var url = require('url');


router.route('/post').post((req, res) => {
    var contant= req.body.contant;
    var paramTreeIndex = req.body.tree_idx;

    var postInput=[contant,paramTreeIndex];
    connection.query(`INSERT INTO post(contant,tree_idx) VALUES(?,?)`,postInput, (err, rows) => {
        if(err) {
            throw err;
            res.sendStatus(204);
        }
        console.log('can post');
        res.sendStatus(200);
    });
}).get((req,res)=>{
    var paramTreeIndex = url.parse(req.url,true).query.tree_idx;

    connection.query('select * from post where tree_idx=?',paramTreeIndex,(err, result)=>{
        if(err){
            throw err;
            res.sendStatus(204);
        }
        res.json(result);
        res.status(200);
    })
})
module.exports = router;
