import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import UserDashboard from "./pages/UserDashboard";
import CreateComplaint from "./pages/CreateComplaint";
import MyComplaints from "./pages/MyComplaints";
import Notifications from "./pages/Notifications";
import AdminDashboard from "./pages/AdminDashboard";
import TeamManagement from "./pages/TeamManagement";
import EmployeeDashboard from "./pages/EmployeeDashboard";
import UpdateStatus from "./pages/UpdateStatus";
import ComplaintAssignment from "./pages/ComplaintAssignment";
import AdminComplaints from "./pages/AdminComplaints";
import ComplaintDetails from "./pages/ComplaintDetails";
import ComplaintStatus from "./pages/ComplaintStatus";
import EmployeeComplaints from "./pages/EmployeeComplaints";
import SendNotification from "./pages/SendNotification";
import RaiseQuery from "./pages/RaiseQuery";
import ViewTeams from "./pages/ViewTeams";
import TeamDetails from "./pages/TeamDetails";
import ViewUsers from "./pages/ViewUsers";
import ViewEmployees from "./pages/ViewEmployees";
import UploadDetails from "./pages/UploadDetails";

function App() {
  return (
    <Router>
      <div className="container text-center mt-5">
        
        <h1>Digital Complaint System</h1>

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />

          <Route path="/user" element={<UserDashboard />} />
          <Route path="/create-complaint" element={<CreateComplaint />} />
          <Route path="/my-complaints" element={<MyComplaints />} />
          <Route path="/notifications" element={<Notifications />} />

          <Route path="/admin" element={<AdminDashboard />} />
          <Route path="/team" element={<TeamManagement />} />
          <Route path="/teams" element={<ViewTeams />} />
          <Route path="/team/:id" element={<TeamDetails />} />
          <Route path="/admin/complaints" element={<AdminComplaints />} />
          <Route path="/complaint/:id" element={<ComplaintDetails />} />
          <Route path="/assign/:id" element={<ComplaintAssignment />} />
          <Route path="/status/:id" element={<ComplaintStatus />} />
          <Route path="/admin/users" element={<ViewUsers />} />
          <Route path="/admin/employees" element={<ViewEmployees />} />

          <Route path="/employee" element={<EmployeeDashboard />} />
          <Route path="/employee/complaints" element={<EmployeeComplaints />} />
          <Route path="/update-status/:id" element={<UpdateStatus />} />
          <Route path="/notify/:id" element={<SendNotification />} />

          <Route path="/upload-details" element={<UploadDetails />} />

          <Route path="/query/:id" element={<RaiseQuery />} />
        </Routes>

      </div>
    </Router>
  );
}

export default App;