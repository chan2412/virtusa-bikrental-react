import { ApiClient } from '../../Utils/ApiClient';

async function fetchBookings() {
    var bookings = []
    var value=JSON.parse(localStorage.getItem("@AsyncStorage:USER"));
    await ApiClient.get('/bookings/'+value.user)
    .then(response => {
        if (response.data) {
            bookings = response.data
            console.log(bookings)
            return bookings
        }
    })
    .catch(error => {
        console.log(error)
    })

    return bookings
}

export { fetchBookings }