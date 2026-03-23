import { useNavigate } from "react-router-dom";

function Home() {
  const navigate = useNavigate();

  return (
    <div className="container mt-5">

      {/* Description */}
      <div className="text-center mb-5">
        <p className="fw-bold fs-5">
          A powerful and dependable platform designed to simplify the way you raise, track, and resolve complaints—all in one place.
        </p>
        <p className="fw-bold fs-5">
          Experience complete transparency with real-time updates, clear status tracking, and instant notifications at every stage.
        </p>
        <p className="fw-bold fs-5">
          Built for everyone—fast, intuitive, and efficient—ensuring every issue is handled smoothly and without stress.
        </p>
      </div>

      {/* Two Columns */}
      <div className="row text-center">

        {/* Register Column */}
        <div className="col-md-6 mb-4">
          <h5>New user or don't have an account?</h5>

          <button
            className="btn btn-success btn-lg w-75 mt-3"
            onClick={() => navigate("/register")}
          >
            Register
          </button>
        </div>

        {/* Login Column */}
        <div className="col-md-6 mb-4">
          <h5>Already a user?</h5>

          <button
            className="btn btn-primary btn-lg w-75 mt-3"
            onClick={() => navigate("/login")}
          >
            Login
          </button>
        </div>

      </div>
    </div>
  );
}

export default Home;