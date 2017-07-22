const express = require('express');
const app = express();

const bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({extended: true}));

// const cookieParser = require('cookie-parser');
// app.use(cookieParser('!..d3##dk!'));

// const session = require('express-session');
// app.use(session({
//     secret: '2k@!kkeSS',
//     resave: false,
//     saveUninitialized: true
// }));

app.listen(80, () => {
    console.log('Server is listening');
});
