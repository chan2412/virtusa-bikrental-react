import { ApiClient } from '../Utils/ApiClient';

async function fetchAllBookings() {
    var bookings = []
    await ApiClient.get('/superadmin/bookings')
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
async function fetchAdmins() {
    var admins = []
    await ApiClient.get('/superadmin/admins')
    .then(response => {
        if (response.data) {
            admins = response.data
            console.log(admins)
            return admins
        }
    })
    .catch(error => {
        console.log(error)
    })

    return admins
}

async function deleteAdmin(id) {
    await ApiClient.delete('/superadmin/admin/'+id)
    .then(response => {
        if(response.status === 200){
            return true
        }
    })
}
export { fetchAllBookings,fetchAdmins,deleteAdmin }