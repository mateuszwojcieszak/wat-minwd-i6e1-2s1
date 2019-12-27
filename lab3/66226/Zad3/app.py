# app.py
from flask import Flask, request, render_template, jsonify  # import flask
from github3 import login

app = Flask(__name__)  # create an app instance


@app.route("/", methods=["GET", "POST"])

def home_page():
    return render_template('home.html')

@app.route("/results", methods=["GET", "POST"])

def loaddata():
    if request.method == 'POST':
        y = True
        log = request.form['log']
        pswd = request.form['pswd']
        user1 = request.form['user1']
        x = int(request.form['x'])
        gh = login(log, password=pswd)
        user = gh.user(user1)
        print("Imie:")
        print(user.name)
        print("\n")
        print("Login:")
        print(user.login)
        print("\n")
        print("Email:")
        print(user.email)
        print("\n")
        print("Repositories:")
        repos = [f for f in gh.repositories_by(user)]
        print(*repos, sep="\n")
        print("\n")

        print(user, " Number of following:\n")
        print(user.following_count)
        print("\n")
        for f in user.following():
            print(str(f))

        print("\n")
        print(user, " Number of followers:")
        print(user.followers_count)
        print("\n")

        if x > 0:
            for f in user.followers():
                print("(1) Follower of: ", user, str(f))
                if x > 1:
                    for h in f.followers():
                        if y: print("   (2)Followers of: ", f, ": ")
                        print("     (2)Follower of: ", f, str(h))
                        y = False
                    # y = True
                        if x > 2:
                            for j in h.followers():
                                if y: print("       (3)Followers of: ", h, ": ")
                                print("         (3)Follower of: ", h, str(j))
                                y = False
                            y = True
                            if x == 4:
                                for k in j.followers():
                                    if y: print("           (4)Followers of: ", j, ": ")
                                    print("             (4)Follower of: ", j, str(k))
                                    y = False
                                y = True

        return render_template('results.html')

if __name__ == "__main__":  # on running python app.py
    app.run(debug=True)  # run the flask app
