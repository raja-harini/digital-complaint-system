import { useNavigate } from "react-router-dom";

function UserDashboard() {
  const navigate = useNavigate();

  return (
    <div className="container mt-5 text-center">
      <h2>User Dashboard</h2>

      <div className="mt-4">
        <button className="btn btn-success m-2" onClick={() => navigate("/create-complaint")}>
          Create Complaint
        </button>

        <button className="btn btn-primary m-2" onClick={() => navigate("/my-complaints")}>
          My Complaints
        </button>

        <button className="btn btn-warning m-2" onClick={() => navigate("/notifications")}>
          Notifications
        </button>
      </div>
    </div>
  );
}

export default UserDashboard;