import { ApiClient } from '../../Utils/ApiClient';

async function editUserProfile(profileDetails) {

    const data = {
        email: profileDetails.email,
        age: profileDetails.age,
        password: profileDetails.password,
        mobilenumber: profileDetails.mobilenumber,
        username: profileDetails.username,
        userrole: "user",
    }
    console.log(data);
    var x;
    await ApiClient.put('/user/edit/', data)
        .then(response => {
            x = true
        }).catch(
            err => {
                x = err.response.data
            }
        )
        console.log(x)
    return x;
}

export { editUserProfile }