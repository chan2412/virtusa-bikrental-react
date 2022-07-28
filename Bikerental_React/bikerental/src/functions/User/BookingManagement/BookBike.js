import { ApiClient } from "../../Utils/ApiClient";

async function bookBike(details,company){
    var value=JSON.parse(localStorage.getItem("@AsyncStorage:USER"));
    const data = {
        bookingid:"bid"+Math.random().toString(36).substr(2, 9),
        bikemodel:details.model,
        bikeid:details.bikeid,
        userid:value.user,
        adminid:details.adminid,
        companyname: company,
        rent:details.price,
        days:details.days,
        totalprice:details.totalprice
        }
    console.log(data);
    var x;
    await ApiClient.post('booking/add', data)
    .then(response => {
        if (response) {
           console.log(response)
           x= response.data
        }
    });
    await ApiClient.get('/bike/book/'+data.bikeid).then(response => {
        if(response)
        {
            
            console.log(response.data)
        }
    })
    console.log(x)
    return x;
}

export { bookBike }