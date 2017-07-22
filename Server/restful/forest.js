
const express = require('express');
const router = express.Router();

const connection = require('../mysql.js');

router.route('/forest/add').post ((req,res)=>{
    var treeIndex= req.body.tree_idx;
    var hasarray= req.body.hashtags;
    
    connection.query('INSERT')
})

module.exports = router;
