import { ListItemText } from '@mui/material';
import Avatar from '@mui/material/Avatar';
import Box from '@mui/material/Box';
import Breadcrumbs from '@mui/material/Breadcrumbs';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import IconButton from '@mui/material/IconButton';
import Paper from '@mui/material/Paper';
import { useTheme } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableFooter from '@mui/material/TableFooter';
import TableHead from '@mui/material/TableHead';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import Typography from '@mui/material/Typography';
import { AsyncStorage } from 'AsyncStorage';
import 'material-icons/iconfont/material-icons.css';
import PropTypes from 'prop-types';
import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { fetchCompanyBikes } from '../../../../functions/User/BookingManagement/DisplayCompany';
import AdminHeader from '../../../Header/Header';
import './ViewCompany.css';


function TablePaginationActions(props) {
  const theme = useTheme();
  const { count, page, rowsPerPage, onPageChange } = props;

  const handleFirstPageButtonClick = (event) => {
    onPageChange(event, 0);
  };

  const handleBackButtonClick = (event) => {
    onPageChange(event, page - 1);
  };

  const handleNextButtonClick = (event) => {
    onPageChange(event, page + 1);
  };

  const handleLastPageButtonClick = (event) => {
    onPageChange(event, Math.max(0, Math.ceil(count / rowsPerPage) - 1));
  };

  return (
    <Box sx={{ flexShrink: 0, ml: 2.5 }}>
      <IconButton
        onClick={handleFirstPageButtonClick}
        disabled={page === 0}
        aria-label="first page"
      >
        {theme.direction === 'rtl' ? <span className="material-icons">last_page</span> : <span className="material-icons">first_page</span>}
      </IconButton>
      <IconButton
        onClick={handleBackButtonClick}
        disabled={page === 0}
        aria-label="previous page"
      >
        {theme.direction === 'rtl' ? <span className="material-icons">keyboard_arrow_right</span> : <span className="material-icons">keyboard_arrow_left</span>}
      </IconButton>
      <IconButton
        onClick={handleNextButtonClick}
        disabled={page >= Math.ceil(count / rowsPerPage) - 1}
        aria-label="next page"
      >
        {theme.direction === 'rtl' ? <span className="material-icons">keyboard_arrow_left</span> : <span className="material-icons">keyboard_arrow_right</span>}
      </IconButton>
      <IconButton
        onClick={handleLastPageButtonClick}
        disabled={page >= Math.ceil(count / rowsPerPage) - 1}
        aria-label="last page"
      >
        {theme.direction === 'rtl' ? <span className="material-icons">first_page</span> : <span className="material-icons">last_page</span>}
      </IconButton>
    </Box>
  );
}

TablePaginationActions.propTypes = {
  count: PropTypes.number.isRequired,
  onPageChange: PropTypes.func.isRequired,
  page: PropTypes.number.isRequired,
  rowsPerPage: PropTypes.number.isRequired,
};

function ViewCompany() {
    const {state} = useLocation()
    const {company} = state
    console.log(state,company);
    const [bike, setBikes] = useState([])
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);
  const [deleteModalOpen, setDeleteModalOpen] = React.useState(false);
  let navigate = useNavigate();

  const breadcrumbs = [
    <Typography key="1" color="inherit">
      User
    </Typography>,
    <Typography key="2" color="text.primary">
      Company
    </Typography>,
    <Typography key="3" color="text.primary">
    Bike
  </Typography>
  ];

  const emptyRows =
    page > 0 ? Math.max(0, (1 + page) * rowsPerPage - company.length) : 0;

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };


  const handleClose = () => {
    setDeleteModalOpen(false);
  };

  const handleViewBikes = (bike) => {
    console.log(bike)
    navigate('/user/viewBike', { state: {bike: bike,company:company } })
  }
  useEffect(() => {
    console.log(AsyncStorage.getItem('USER'))
    console.log(company.email);
    fetchCompanyBikes(company.email).then((fetchedBikes) => {
        if (bike.length !== fetchedBikes.length) {
          setBikes(fetchedBikes)
        }
        if (bike.length === 0) {
          setBikes(fetchedBikes)
        }
      })
  }, [bike])

  return (
    <div>
      <AdminHeader highlight={"Company"} />
      <div className="Viewcompany-Nav">
        <Avatar sx={{ width: 30, height: 30, marginRight: '8px' }}>
          <span className="material-icons">person</span>
        </Avatar>
        <Breadcrumbs separator="›" aria-label="breadcrumb">
          {breadcrumbs}
        </Breadcrumbs>
      </div>
      <div className="details">
                <table className='viewcompanytable'>
                    
                        <tr>
                        <th>Company Name</th>
                        <td>:</td>
                        <td><ListItemText>{company?company.companyname:''}</ListItemText></td>
                    </tr>
                    <tr>
                    <th>Company Address</th>
                        <td>:</td>
                    <td><ListItemText> {company?company.companyaddress:''}</ListItemText></td>
                    </tr>
                    <tr>
                    <th>Mobile Number</th>
                        <td>:</td>
                    <td><ListItemText> {company?company.mobilenumber:''}</ListItemText></td>
                    </tr>
                        
                </table>
                </div>
      <TableContainer className="Viewcompany-Table" component={Paper}>
        <Table sx={{ minWidth: 500 }} aria-label="custom pagination table">
          <TableHead>
            <TableRow>
              <TableCell align="center">Bike Model</TableCell>
              <TableCell align="center">Price</TableCell>
              <TableCell align="center">Type</TableCell>
              <TableCell align="center">Availability</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {(rowsPerPage > 0
              ? bike.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
              : bike
            ).map((row) => (
              <TableRow key={row.bikeid} onClick={()=>{handleViewBikes(row)}}>
                
                <TableCell style={{ width: 160 }} align="center">
                  {row.model}
                </TableCell>
                <TableCell style={{ width: 160 }} align="center">
                  {row.price}
                </TableCell>
                <TableCell style={{ width: 160 }} align="center">
                  {row.type}
                </TableCell>
                <TableCell style={{ width: 160 }} align="center">
                  {row.status==="yes"?"available":"Booked"}
                </TableCell>
                
              </TableRow>
            ))}

            {emptyRows > 0 && (
              <TableRow style={{ height: 53 * emptyRows }}>
                <TableCell colSpan={6} />
              </TableRow>
            )}
          </TableBody>
          <TableFooter>
            <TableRow>
              <TablePagination
                rowsPerPageOptions={[5, 10, 25, { label: 'All', value: -1 }]}
                colSpan={6}
                count={bike.length}
                rowsPerPage={rowsPerPage}
                page={page}
                SelectProps={{
                  inputProps: {
                    'aria-label': 'rows per page',
                  },
                  native: true,
                }}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handleChangeRowsPerPage}
                ActionsComponent={TablePaginationActions}
              />
            </TableRow>
          </TableFooter>
        </Table>
      </TableContainer>
      
      <Dialog
        open={deleteModalOpen}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
          {"Delete User"}
        </DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            Are you sure? This action cannot be undone.
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button  color="primary">Yes</Button>
          <Button onClick={handleClose} color="success" autoFocus>
            Cancel
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  )
}

export default ViewCompany