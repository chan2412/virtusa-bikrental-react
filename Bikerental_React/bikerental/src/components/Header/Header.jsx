import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Container from '@mui/material/Container';
import IconButton from '@mui/material/IconButton';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import React, { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import { resetAuthorizationHeader } from '../../functions/Utils/ApiClient';
import { actionTypes } from '../../functions/Utils/Reducer';
import { useStateValue } from '../../functions/Utils/StateProvider';
import './Header.css';

function AdminHeader({ highlight}) {

  const [{userType}, dispatch] = useStateValue();
  const [menuItems, setMenuItems] = useState([]);
  const [anchorElNav, setAnchorElNav] = useState(null);
  let navigate = useNavigate();

  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };
  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };
  const logoutUser = () => {
    console.log("logging out")
    dispatch({
      type: actionTypes.SET_USER,
      user: null,
      jwt:null,
      userType: null
    })
    // AsyncStorage.remove("USER");
    // AsyncStorage.remove("USER");
    localStorage.removeItem("@AsyncStorage:USER");
    localStorage.removeItem("@AsyncStorage:jwt");
    resetAuthorizationHeader();
    navigate('/')
  }

  useEffect(() => {
    userType.toLowerCase() === 'admin' 
      ? setMenuItems([{"name":'Dashboard', "route": '/admin/displayBikes'}, {"name":'Profile', "route": '/admin/displayProfile'}, {"name":'Bookings', "route": '/admin/displayBookings'}]) 
      : userType.toLowerCase() === 'superadmin' 
        ? setMenuItems([{"name":'Dashboard', "route": '/superadmin/dashboard'}, {"name":'Bookings', "route": '/superadmin/bookings'}])
        : setMenuItems([{"name":'Dashboard', "route": '/user/displayCompany'}, {"name":'Profile', "route": '/user/displayProfile'}, {"name":'MyBookings', "route": '/user/mybookings'}])
  }, [userType])

  return (
    <AppBar position="static">
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          <Typography
            noWrap
            variant="h4"
            component="div"
            sx={{ display: { xs: 'none', md: 'flex' } }}
          >
            Neo Bikes
          </Typography>

          <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
            <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleOpenNavMenu}
              color="inherit"
            >
              <span className="material-icons">menu</span>
            </IconButton>
            <Menu
              id="menu-appbar"
              anchorEl={anchorElNav}
              anchorOrigin={{
                vertical: 'bottom',
                horizontal: 'left',
              }}
              keepMounted
              transformOrigin={{
                vertical: 'top',
                horizontal: 'left',
              }}
              open={Boolean(anchorElNav)}
              onClose={handleCloseNavMenu}
              sx={{
                display: { xs: 'block', md: 'none' },
              }}
            >
              {
                menuItems.map((menuItem) => (
                  <MenuItem key={menuItem.name} onClick={() => navigate(menuItem.route)}>
                    <Typography  textAlign="center">{menuItem.name}</Typography>
                  </MenuItem>
                ))
              }
            </Menu>
          </Box>

          <Typography
            noWrap
            variant="h4"
            component="div"
            sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}
          >
            NeoBikes
          </Typography>
          <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' }, marginLeft: '30px' }}>
            {
              menuItems.map(menuItem => (
                <Button 
                  key={menuItem.name} 
                  onClick={() => navigate(menuItem.route)}  
                  className="Header-Button"            
                  sx={{ my: 2, color: 'white', display: 'block' }}
                >
                  {menuItem.name}
                </Button>
              ))
            }
          </Box>

          <Button color="inherit" onClick={() => logoutUser()}>Logout</Button>
        </Toolbar>
      </Container>
    </AppBar>
  )
}

export default AdminHeader