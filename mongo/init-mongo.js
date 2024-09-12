db.createUser(
    {
      user: "ourcompanylunchnotiuser",
      pwd: "a1234",
      roles: [
        {
          role: "readWrite",
          db: "ourcompanylunchnoti"
        }
      ]
    }
);
