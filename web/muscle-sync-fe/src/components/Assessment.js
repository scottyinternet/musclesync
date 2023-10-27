import React, { useState } from 'react';
import Slider from './Slider';

export default function Assessment() {

      const [data, setData] = useState([
        { id: 1, title: 'Primary : Breath Twisty Test', value1: -90, value2: -20, sliderValue: 0, notes: '' },
        { id: 2, title: 'Secondary : Some Other Test 2', value1: -20, value2: -10, sliderValue: 0, notes: '' },
        { id: 3, title: 'Secondary : Some Other Test 2 2', value1: -40, value2: -10, sliderValue: 0, notes: '' },
        { id: 4, title: 'Secondary : Some Other Test 2 2', value1: -50, value2: -10, sliderValue: 0, notes: '' },
        { id: 5, title: 'Secondary : Some Other Test 2 2', value1: -50, value2: -10, sliderValue: 0, notes: '' },
        // Add more rows as needed
      ]);
    
      const handleSliderChange = (id, newValue) => {
        setData((prevData) =>
          prevData.map((item) => (item.id === id ? { ...item, sliderValue: newValue } : item))
        );
      };


    const handleOptionClick = (id, selectedOption) => {
        console.log(`Option clicked for item ${id}: ${selectedOption}`);
    }

      const options = ['Narrow', 'Wide'];
    
      const handleNotesChange = (id, newNotes) => {
        setData(prevData => prevData.map(item => (item.id === id ? { ...item, notes: newNotes } : item)));
      };

    
      return (
        <div>
            <h3>Infrasternal Angle</h3>
          <table>
            <tbody>
              {data.map(item => (
                <tr key={item.id}>
                    <td>{item.title}</td>
                    <td>{item.value1}</td>
                    <td>{item.value2}</td>
                    
                    <td>
                    <Slider
                        value={item.sliderValue}
                        onChange={(e) => handleSliderChange(item.id, e.target.value)}
                        onOptionClick={(selectedOption) => handleOptionClick(item.id, selectedOption)}
                        options={options}
                    />
                    
                    </td>
                    <td>
                    <textarea
                      value={item.notes}
                      onChange={(e) => handleNotesChange(item.id, e.target.value)}
                    />
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      );
}