import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';

const SignInPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [rememberMe, setRememberMe] = useState(false);
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log({username, password, rememberMe});
    };

    return (
        <div className="sign-in-page">
            <div className="form-container">
                <button onClick={() => navigate('/')} className="btn btn-secondary mb-3">
                    Back to Home
                </button>
                {}
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="exampleInputUsername1">Username address</label>
                        <input
                            type="username"
                            className="form-control"
                            id="exampleInputUsername1"
                            aria-describedby="usernameHelp"
                            placeholder="Enter Username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                        />
                        <small id="usernameHelp" className="form-text text-muted">
                            We'll never share your username with anyone else.
                        </small>
                    </div>
                    <div className="form-group">
                        <label htmlFor="exampleInputPassword1">Password</label>
                        <input
                            type="password"
                            className="form-control"
                            id="exampleInputPassword1"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>
                    <div className="form-group form-check">
                        <input
                            type="checkbox"
                            className="form-check-input"
                            id="exampleCheck1"
                            checked={rememberMe}
                            onChange={() => setRememberMe(!rememberMe)}
                        />
                        <label className="form-check-label" htmlFor="exampleCheck1">
                            Remember me
                        </label>
                    </div>
                    <button type="submit" className="btn btn-primary">
                        Submit
                    </button>
                </form>
            </div>
        </div>
    );
};

export default SignInPage;
