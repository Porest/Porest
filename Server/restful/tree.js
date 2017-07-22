// Auther : JoMingyu

const express = require('express');
const router = express.Router();

const connection = require('../mysql.js');
const util = require('util');
var url = require('url');

const date = new Date();

router.route('/tree').post((req, res) => {
    // 나무 만들기
    let name= req.body.tree_name;
    let id = req.body.id;
    let maximumLeaves = req.body.maximum_leaves;
    let currentDate = date.toISOString().replace(/T/, ' ').replace(/\..+/, '');

    connection.query(`INSERT INTO tree(tree_name,owner, creation_time, maximum_leaves) VALUES('${name}','${id}', '${currentDate}', ${maximumLeaves})`, (err, rows) => {
        if(!err) {
            throw err;
            res.sendStatus(204);
        }
    res.sendStatus(200);
    });
}).get((req, res) => {
    // 나무 리스트
    
    var paramid = url.parse(req.url,true).query.id;
    console.log(paramid);
    connection.query('select * from tree where owner=?',paramid,(err, result)=>{
        if(err){
            throw err;
            res.sendStatus(204);
        }
        console.log(result);
        res.json(result);
    })
}).delete((req,res)=>{
    var paramTreeIndex= req.body.tree_idx;
    connection.query('delete from tree where tree_idx=?',paramTreeIndex,(err,result)=>{
        if(err){
            throw err;
            res.sendStatus(204);
        }
        console.log(result);
        res.sendStatus(200);
    });
})

module.exports = router;
