import { AsyncStorage } from 'AsyncStorage';
import { ApiClient } from '../../Utils/ApiClient';

async function addBike(bikeDetails) {
    var x;
    AsyncStorage.getItem('USER').then(async (value) => {
        if (value) {
          value = JSON.parse(value)
          x=value;
          console.log(x);
          const data = {
              bikeid: "id"+(new Date()).getTime(),
              bikeno: bikeDetails.bikeno,
              adminid: x.user,
              model:bikeDetails.model,
              status: "available",
              bikeimageurl:bikeDetails.bikeimageurl,
              price: bikeDetails.price,
              type: bikeDetails.type
          }
      console.log(data);
          await ApiClient.post('/bike/add/', data)
          .then(response => {
              if(response.status === 200){
                  return true
              }
          })
        }
    }
    );
   
}

export { addBike }