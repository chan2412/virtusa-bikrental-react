import { ApiClient } from '../../Utils/ApiClient';

async function fetchMyBookings() {
    var bookings = []
    var value=JSON.parse(localStorage.getItem("@AsyncStorage:USER"));
    await ApiClient.get('/mybookings/'+value.user)
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

export { fetchMyBookings }