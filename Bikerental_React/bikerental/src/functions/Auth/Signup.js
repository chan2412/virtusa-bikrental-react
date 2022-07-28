import { ApiClient } from '../Utils/ApiClient';

function validateFields(values){
    let res = {
        status : true,
        error: ''
    };
    
    let emailRegex 
    if (values.email !== "admin"){
        emailRegex = values.email.toLowerCase().match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)
    }else{
        emailRegex = true
    }

    let phnRegex = values.mobilenumber.match(/^[+]?[(]?[0-9]{3}[)]?[-\s.]?[0-9]{3}[-\s.]?[0-9]{4,6}$/);
    if(!emailRegex){
        res.error = "Invalid Email"
        return res
    }
    if(!phnRegex){
        res.error ='Invalid Phone No.'
        return res
    }
    if(values.age)
    {
        if(!(values.age>0 && values.age<100))
        {
        res.error = 'Invalid age'
        return res
        }
    }

    res  = {
        status : false,
        error: ''
    }

    return res
}

async function saveUser(details){
    const data = {
        email : details.email,
        password: details.password,
        username: details.username,
        userrole:"user",
        mobilenumber: details.mobilenumber,
        age:details.age
    }
    console.log(details);
    await ApiClient.post('/user/register' , data)
    .then(response => {
        if (response) {
            return response
        }
    });
}


async function saveAdmin(details){
    const data = {
        id:"id"+Math.random().toString(36).substr(2, 9),
        email : details.email,
        password: details.password,
        username: details.username,
        mobilenumber: details.mobilenumber,
        sellername: details.sellername,
        userrole: "admin",
        companyname: details.companyname,
        companyimageurl: details.companyimageurl,
        companyaddress: details.companyaddress
    }
    console.log(data);
    await ApiClient.post('/admin/register', data)
    .then(response => {
        if (response) {
            return response
        }
    });
}

export { validateFields, saveUser , saveAdmin }