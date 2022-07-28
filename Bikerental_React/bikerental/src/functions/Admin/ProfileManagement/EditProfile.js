import { ApiClient } from '../../Utils/ApiClient';

async function editProfile(profileDetails) {

    const data = {
        id: profileDetails.id,
        email: profileDetails.email,
        password: profileDetails.password,
        mobilenumber: profileDetails.mobilenumber,
        sellername: profileDetails.sellername,
        userrole: "admin",
        companyname: profileDetails.companyname,
        companyimageurl: profileDetails.companyimageurl,
        companyaddress: profileDetails.companyaddress,
        earnings: profileDetails.earnings
    }
    console.log(data);
    var x;
    await ApiClient.put('/admin/edit/', data)
        .then(response => {
            x = true
        }).catch(
            err => {
                x = err.response.data
            })
            return x;
}

export { editProfile };
