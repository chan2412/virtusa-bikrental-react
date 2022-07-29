import { Avatar, Button, ListItemText, Typography } from '@mui/material';
import Breadcrumbs from '@mui/material/Breadcrumbs';
import React from 'react';
import { useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import AdminHeader from '../../../Header/Header';
import "./ViewBike.css";
const breadcrumbs = [
    <Typography key="1" color="inherit">
      User
    </Typography>,
   <Typography key="2" color="text.primary">
    Company
  </Typography>,
    <Typography key="3" color="text.primary"
    >
      Bike
    </Typography>
];

export default function ViewBike() {
  const {state} = useLocation()
  const {bike} = state
  const {company}=state
  let navigate = useNavigate();
    // useEffect(() => {
    //     fetchBike().then( (fetchedBike) => {
    //         setBike(fetchedBike)
    //     })
    //   }, [bike])
    useEffect(()=>{
console.log(bike);
    },[]);
      const handleBookBike = () => {
        navigate('/user/bookBike', { state: { bike: bike,company:company } })
      }
    return (
        <div>
            <AdminHeader highlight={"Profile"} />
            <div className="ViewBike-Nav">
                <Avatar sx={{ width: 30, height: 30, marginRight: '8px' }}>
                    <span className="material-icons">person</span>
                </Avatar>
                <Breadcrumbs separator="›" aria-label="breadcrumb">
                    {breadcrumbs}
                </Breadcrumbs>
            </div>
            <div className='Bike-container'>
            <div className="Bike-details1">
            {/* <img src={bike?(bike.type==="Bike"?"https://img.etimg.com/thumb/msid-29445066,width-1200,height-900,imgsize-54253,overlay-economictimes/photo.jpg":"https://images.carandbike.com/bike-images/colors/honda/activa-5g/honda-activa-5g-dazzle-yellow-metallic.png"):""} alt="CompanyImage" width={300} height={300} className="BikePic-center" /> */}
            <img src={bike.bikeimageurl} alt="BikeImage" width={300} height={300} className="BikePic-center" />
            </div>
            <div className="Bike-details2">
                <table className="viewbiketable">
                    <tr>
                        <th>Bikemodel</th>
                        <td>:</td>
                        <td><ListItemText> {bike?bike.model:''}</ListItemText></td>
                        </tr>
                        <tr>
                        <th>Price</th>
                        <td>:</td>
                        <td><ListItemText>{bike?bike.price:''}</ListItemText></td>
                    </tr>
                    <tr>
                    <th>Type</th>
                        <td>:</td>
                    <td><ListItemText> {bike?bike.type:''}</ListItemText></td>
                    </tr>
                </table>
                <Button  onClick={()=>{handleBookBike()}} variant="contained" size={"large"} id="editProfile" disabled={!(bike.status==="available")} >Book</Button>
            </div>
            </div>
        </div>
    )
}
