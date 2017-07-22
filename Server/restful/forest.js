
const express = require('express');
const router = express.Router();

const connection = require('../mysql.js');
var sync= require('sync');
const util = require('util');
const date = new Date();
const currentDate = date.toISOString().replace(/T/, ' ').replace(/\..+/, '');

router.route('/forest/add').post((req,res)=>{
    var treeIndex= req.body.tree_idx;
    // var hasarray= req.body.hashtags;
    var hasarray= ['hi','ho','he'];
    var hashIndex;
    console.log('in forest add');
    connection.query('select is_shared from tree',(err, result)=>{
        if(err){
            throw err;
            console.log('is_shared select err');
            res.sendStatus(204);
        }
        if(result){
            connection.query('update tree set is_shared=? where tree_idx=?',[1,treeIndex],(err,result)=>{
                if(err){
                    throw err;
                    
                    console.log('update is_shared');
                    res.sendStatus(203);
                }
                console.log(result);
            });
        }
    });
    var forestInput=[treeIndex,currentDate];
    connection.query('insert into forest(tree_idx,creation_time) values(?,?)',forestInput,(err, result)=>{
        if(err){
            throw err;
            console.log('err');
            res.sendStatus(204);
        }
        console.log(result);
        
        connection.query('select hash_idx from forest where tree_idx=?',treeIndex,(err,result)=>{
            if(err){
                throw err;
                console.log(err);
                res.sendStatus(204);
            }    
            hashIndex=result[0].hash_idx;

            for(var i=0; i<hasarray.length;i++){
                var tagsInput=[hashIndex,hasarray[i]];
                connection.query('insert into tags(hash_idx,contant) values(?,?)',tagsInput,(err, result)=>{
                    if(err){
                        throw err;
                        console.log('tags err');
                        res.sendStatus(204);
                    }
                    console.log(result);
                })
                if(i==3){
                    res.sendStatus(200);
                }
            }
        })
    })
});
router.route('/forest/random/select').get((req,res)=>{
    console.log('in forest random');
    connection.query('select * from tree where is_shared=?',1,(err,result)=>{
        if(err){
            throw err;
            console.log('select random tree err');
            res.sendStatus(204);
        }
        console.log(result);
        res.json(result);
        res.sendStatus(200);
    })
});
router.route('/forest/tag/select').post((req,res)=>{
    console.log('in forest tag');
    var tag=req.body.contant;
    connection.query('select hash_idx from tags where contant=?',tag,(err,result)=>{
        if(err){
            throw err;
            console.log('select tag tree err');
            res.sendStatus(204);
        }
        var tagArray=result;
        
        function forfunction(){
            var treeArray=[];
                for(var i=0;i<tagArray.length;i++){
                    connection.query('select tree_idx from forest where hash_idx=?',tagArray[i].hash_idx,(err,result)=>{
                        if(err){
                            throw err;
                            console.log('select tag tree err');
                            res.sendStatus(204);
                        } 
                        // console.log(result);
                        
                        
                        treeArray.push(result[0].tree_idx);
                        console.log(result[0].tree_idx);
                        // connection.query('select ')
                    })
                }
            return treeArray;
        }
        sync(function(){
            
            var result= forfunction.sync();
            console.log(result); 
            console.log('fsdf');  
        })
        
            
        
    })
});

module.exports = router;
