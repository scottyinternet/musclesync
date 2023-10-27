import React, { useState } from 'react';
import Assessment from './Assessment';
import '../styles.css';

export default function Client() {
    const [showNewAssessment, setShowNewAssessment] = useState(false);

    const handleNewAssessmentClick = () => {
        console.log("i was clicked, hurray for scott!")
        setShowNewAssessment(prevShow => !prevShow);
    };


    return <>
    
            <div>
                <h1>Hey Scott, good job!</h1>
                <button onClick={handleNewAssessmentClick} className="button">New Assessment</button>
                <hr></hr>
            </div>

            
            {showNewAssessment && <Assessment />}

        </>
        
}
