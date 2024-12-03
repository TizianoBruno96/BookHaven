import React from 'react';
import {useNavigate} from 'react-router-dom';

const ProfilePage = () => {
    const navigate = useNavigate();
    const user = JSON.parse(localStorage.getItem('user'));

    if (!user) {
        navigate('/signin');
        return null;
    }

    const userInfo = {
        email: "glauco.avellino@example.com",
        username: user.username,
        age: 37,
        gender: "MALE",
        bio: "Mystery solver and part-time puzzle creator.",
    };

    return (
        <div className="profile-container">
            <h2>Profile: {userInfo.username}</h2>
            <div className="profile-details">
                <p><strong>Email:</strong> {userInfo.email}</p>
                <p><strong>Age:</strong> {userInfo.age}</p>
                <p><strong>Gender:</strong> {userInfo.gender}</p>
                <p><strong>Bio:</strong> {userInfo.bio}</p>
            </div>
        </div>
    );
};

export default ProfilePage;
