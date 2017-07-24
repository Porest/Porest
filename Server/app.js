// Auther : JoMingyu

const express = require('express');
const app = express();

const bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true }));

// const cookieParser = require('cookie-parser');
// app.use(cookieParser('!..d3##dk!'));

// const session = require('express-session');
// app.use(session({
//     secret: '2k@!kkeSS',
//     resave: false,
//     saveUninitialized: true
// }));

app.use('', require('./restful/login.js'));
app.use('', require('./restful/tree.js'));
app.use('', require('./restful/forest.js'));
app.use('', require('./restful/post.js'));

app.listen(13958, () => {
    console.log('Server is listening:13958');
});
