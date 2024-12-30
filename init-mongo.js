db.createUser({
    user: "admin",
    pwd: "admin123",
    roles: [
        {
            role: "root",
            db: "admin"
        }
    ]
});

db = db.getSiblingDB('lotto');
db.createCollection("winning_numbers");
