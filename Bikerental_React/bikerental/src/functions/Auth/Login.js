import { ApiClient } from '../Utils/ApiClient';

async function validateUser(email, password) {
    var res = {
        status : false
    }
    const data = {
        username: email,
        password: password
    }
console.log(data);

    await ApiClient.post('/authenticate', data)
    .then(response => {
        console.log(response.data);
        if(response.data){
            res = {
                status: true,
                jwt:response.data.jwt,
                userID:response.data.userid,
                userRole: response.data.userrole
            }
            console.log(res);
        }
        else{
            console.log(response)
        }
    });

    return res
}


export { validateUser }