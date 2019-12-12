import React, { useEffect, useState } from 'react'
import WrappedNormalLoginForm from './components/FormTwitter'
import UserDetails from './components/UserDetails'
import { fetchUserDetails, fetchLastTwitts, fetchLastTwittsTest } from './service/TwitterService'

const App = () => {
  const [formData, setFormData] = useState({})
  const [userDetails, setUserDetails] = useState({})
  const [lastTwitts, setLastTwitts] = useState({})

  useEffect(() => {
    const asyncFun = (formData) => {
      if (Object.keys(formData).length !== 0) {
        Promise.all([fetchUserDetails(formData), fetchLastTwittsTest(formData)])
          .then(res => {
            setUserDetails(res[0])
            setLastTwitts(res[1])
          })
      }
    }
    asyncFun(formData)
  }, [formData])

  return (
    <div>
      <WrappedNormalLoginForm setFormData={setFormData} />
      {Object.keys(userDetails).length !== 0
        && Object.keys(lastTwitts).length !== 0
        && <UserDetails userDetails={userDetails} lastTwitts={lastTwitts} />}
    </div>
  )

}

export default App;
