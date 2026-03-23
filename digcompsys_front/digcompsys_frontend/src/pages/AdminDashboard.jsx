import { useNavigate } from "react-router-dom";

function AdminDashboard() {
  const navigate = useNavigate();

  return (
    <div className="container mt-5 text-center">
      <h2>Admin Dashboard</h2>

      <div className="mt-4">
        <button
          className="btn btn-primary m-2 btn-lg"
          onClick={() => navigate("/team")}
        >
          Team Management
        </button>

        <button
          className="btn btn-dark m-2 btn-lg"
          onClick={() => navigate("/teams")}
        >
          View Teams
        </button>

        <button
          className="btn btn-secondary m-2 btn-lg"
          onClick={() => navigate("/admin/complaints")}
        >
          View Complaints
        </button>

        <button
          className="btn btn-success m-2 btn-lg"
          onClick={() => navigate("/admin/users")}
        >
          View Users
        </button>

        <button
          className="btn btn-warning m-2 btn-lg"
          onClick={() => navigate("/admin/employees")}
        >
          View Employees
        </button>
      </div>
    </div>
  );
}

export default AdminDashboard;