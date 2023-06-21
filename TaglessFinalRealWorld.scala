//> using scala "3.3.0"

object TaglessFinalRealWorld {
    trait UserLogin[E[_]] {
        def checkLogin(boolean: Boolean): E[Boolean]
        def lastErrorStatus(code: Int): E[Int]
        def mfa_v1(email: E[Boolean], sms: E[Boolean]): E[Boolean]
        def mfa_v2(phone: E[Boolean], mobileApp: E[Boolean]): E[Boolean]
        def totalSessionLogins(server1Logins: E[Int], server2Logins: E[Int]): E[Int]
    }

    case class UserLoginStatus[A](value: A)

    given loginCapabilityImplementation: UserLogin[UserLoginStatus] with {
        override def checkLogin(mfa: Boolean) = UserLoginStatus(mfa)
        override def lastErrorStatus(code: Int) = UserLoginStatus(code)
        override def mfa_v1(email: UserLoginStatus[Boolean], sms: UserLoginStatus[Boolean]) = UserLoginStatus(email.value || sms.value)
        override def mfa_v2(phone: UserLoginStatus[Boolean], mobileApp: UserLoginStatus[Boolean]) = UserLoginStatus(phone.value && mobileApp.value)
        override def totalSessionLogins(server1Logins: UserLoginStatus[Int], server2Logins: UserLoginStatus[Int]) = UserLoginStatus(server1Logins.value + server2Logins.value)
    }

    def userLoginFlow[E[_]](using alg: UserLogin[E]): E[Boolean] = {
        import alg.*
        mfa_v1(checkLogin(true), mfa_v2(checkLogin(true), checkLogin(false)))
    }

    def checkLastStatus[E[_]](using alg: UserLogin[E]): E[Int] = {
        import alg.*
        totalSessionLogins(lastErrorStatus(24), lastErrorStatus(-3))
    }
}

def demoTagless: Unit = {
    import TaglessFinalRealWorld.*
    println(userLoginFlow[UserLoginStatus])
    println(checkLastStatus[UserLoginStatus])
}

@main
def main =
    demoTagless
