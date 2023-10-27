import React from 'react';
import '../styles.css';

export default function Slider ({ value, onChange, onSnapToZero, onOptionClick, options }) {
    const optionWidth = `${100 / options.length}%`; // Calculate width for even spacing
  
    return (
      <div className="slider-component">
        <div className="options">
          {options.map((option, index) => (
            <div
              key={index}
              className="option"
              style={{ width: optionWidth }}
              onClick={() => onOptionClick(option)}
            >
              {option}
            </div>
          ))}
        </div>
        <div className="value-readout">{value}</div>
        <input type="range" min={-100} max={100} value={value} onChange={onChange} />
      </div>
    );
  }
  