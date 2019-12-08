package spbpu.hsamcp.mathgame

import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import spbpu.hsamcp.mathgame.mathResolver.MathResolver

@RunWith(AndroidJUnit4::class)
class MathResolverTest {
    @Test
    fun test1() {
        val origin = "1+(1/2)*(cos(x)/2)"
        val actual = MathResolver.resolveToPlain(origin).toString()
        val expected =
            "  1 cos(x)\n" +
            "1+—*——————\n" +
            "  2    2  \n"
        assertEquals(expected, actual)
    }

    @Test
    fun test2() {
        val origin = "1 / 81278 + ((10 / 232 / 3) * (3.78 / 2)) / 2"
        val actual = MathResolver.resolveToPlain(origin).toString()
        // TODO: wrong base
        val expected =
            "       10     \n" +
            "      ——— 3.78\n" +
            "  1   232*————\n" +
            "—————+———   2 \n" +
            "81278  3      \n" +
            "      ————————\n" +
            "          2   \n"
        assertEquals(expected, actual)
    }

    @Test
    fun test3() {
        val origin = "(10/232/2+3/2)/(1/32+1255673645564/33)"
        val actual = MathResolver.resolveToPlain(origin).toString()
        val expected =
            "       10       \n" +
            "      ——— 3     \n" +
            "      232+—     \n" +
            "      ——— 2     \n" +
            "       2        \n" +
            "————————————————\n" +
            " 1 1255673645564\n" +
            "——+—————————————\n" +
            "32       33     \n"
        assertEquals(expected, actual)
    }

    @Test
    fun test4() {
        val origin = "1/((113 + 4)/2)"
        val actual = MathResolver.resolveToPlain(origin).toString()
        val expected =
            "  1  \n" +
            "—————\n" +
            "113+4\n" +
            "—————\n" +
            "  2  \n"
        assertEquals(expected, actual)
    }

    @Test
    fun test5() {
        val origin = "((1/2+((cos(x-3/2)*(tg(x)/ctg(x)))/sin(-x+(x+y)/2))*(14*sin(x*y/2)))/(-(-35+x/2)))^(-1/2)"
        val actual = MathResolver.resolveToPlain(origin).toString()
        val expected =
            "                                 1 \n" +
            "                               (-—)\n" +
            "                                 2 \n" +
            "         3   tg(x)                 \n" +
            "   cos(x-—)*——————                 \n" +
            " 1       2  ctg(x)        x*y      \n" +
            " —+———————————————*14*sin(———)     \n" +
            " 2          x+y            2       \n" +
            "(    sin(-x+———)              )    \n" +
            "             2                     \n" +
            " —————————————————————————————     \n" +
            "                  x                \n" +
            "            -(-35+—)               \n" +
            "                  2                \n"
        assertEquals(expected, actual)
    }

    @Test
    fun test6() {
        val origin = "1/2+cos(x+3/2)"
        val actual = MathResolver.resolveToPlain(origin).toString()
        val expected =
            "1       3 \n" +
            "—+cos(x+—)\n" +
            "2       2 \n"
        assertEquals(expected, actual)
    }

    @Test
    fun test7() {
        val origin = "cos(x)/(1+sin(x))+cos(x)/(1+sin(x/2))"
        val actual = MathResolver.resolveToPlain(origin).toString()
        // TODO: wrong base
        val expected =
            "          cos(x) \n" +
            " cos(x)  ————————\n" +
            "————————+      x \n" +
            "1+sin(x) 1+sin(—)\n" +
            "               2 \n"
        assertEquals(expected, actual)
    }

    @Test
    fun test8() {
        val origin = "(cos(x/2)^2)/(1^(1/2))"
        val actual = MathResolver.resolveToPlain(origin).toString()
        val expected =
            "      2\n" +
            "    x  \n" +
            "cos(—) \n" +
            "    2  \n" +
            "———————\n" +
            "    1  \n" +
            "   (—) \n" +
            "    2  \n" +
            "  1    \n"
        assertEquals(expected, actual)
    }

    @Test
    fun test9() {
        val origin = "(sin(x)/cos(x))^(2^(cos(x/2)/sin((y+4)*2)+8))"
        val actual = MathResolver.resolveToPlain(origin).toString()
        // TODO: wrong base
        val expected =
            "                 x       \n" +
            "             cos(—)      \n" +
            "         (       2    +8)\n" +
            "          ————————————   \n" +
            "          sin((y+4)*2)   \n" +
            "        2                \n" +
            " sin(x)                  \n" +
            "(——————)                 \n" +
            " cos(x)                  \n"
        assertEquals(expected, actual)
    }

    @Test
    fun test10() {
        val origin = "(1+2)*3"
        val actual = MathResolver.resolveToPlain(origin).toString()
        val expected = "(1+2)*3\n"
        assertEquals(expected, actual)
    }

    @Test
    fun test11() {
        val origin = "-1^(-(2+3))"
        val actual = MathResolver.resolveToPlain(origin).toString()
        val expected =
            "  (-(2+3))\n" +
            "-1        \n"
        assertEquals(expected, actual)
    }

    @Test
    fun test12() {
        val origin = "tg((-2)/x)^(-(cos(x)/(1-sin(x))+cos(x)/(1+sin(x))))"
        val actual = MathResolver.resolveToPlain(origin).toString()
        val expected =
            "          cos(x)   cos(x)   \n" +
            "      (-(————————+————————))\n" +
            "         1-sin(x) 1+sin(x)  \n" +
            "   -2                       \n" +
            "tg(——)                      \n" +
            "    x                       \n"
        assertEquals(expected, actual)
    }

    @Test
    fun test13() {
        val origin = "cos(-a)*(-4)"
        val actual = MathResolver.resolveToPlain(origin).toString()
        val expected = "cos(-a)*(-4)\n"
        assertEquals(expected, actual)
    }

    @Test
    fun test14() {
        val origin = "-x+y"
        val actual = MathResolver.resolveToPlain(origin).toString()
        val expected = "-x+y\n"
        assertEquals(expected, actual)
    }

    @Test
    fun test15() {
        val origin = "(1/2)^2"
        val actual = MathResolver.resolveToPlain(origin).toString()
        val expected =
            "   2\n" +
            " 1  \n" +
            "(—) \n" +
            " 2  \n"
        assertEquals(expected, actual)
    }
}