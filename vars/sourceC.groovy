def gitPull() {
    echo "git pull on my repo"
}

def gitClone(String branch, String repo) {
    // git $(command)
    echo  "git clone -b ${branch} ${repo}"
}

