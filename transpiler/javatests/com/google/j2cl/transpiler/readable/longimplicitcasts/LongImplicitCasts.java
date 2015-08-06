package com.google.j2cl.transpiler.readable.longimplicitcasts;

public class LongImplicitCasts {
  // Initialized with not-a-long.
  public long fieldLong = 100;

  @SuppressWarnings("unused")
  public void compareLong(long leftValue) {}

  public LongImplicitCasts() {
    this(100);
  }

  public LongImplicitCasts(long someValue) {}

  @SuppressWarnings("unused")
  public void test() {
    byte fbyte = 11;
    char fchar = 12;
    short fshort = 13;
    int fint = 14;
    long flong = 15;
    float ffloat = 16;
    double fdouble = 17;

    // Initialized with not-a-long.
    long tlong = 0;

    // Direct assignments from smaller types.
    {
      tlong = fbyte;
      tlong = flong;
    }

    // Implicit casts to long when performing any assignment binary operation on a long and any
    // non-long type.
    {
      tlong = fint;
      tlong += fint;
      tlong <<= fint; // Does not cast to long on right hand side.
    }

    // Implicit casts to long when performing the PLUS_EQUALS binary operation on a long and any
    // non-long type.
    {
      tlong += fchar;
      tlong += flong;
      tlong += ffloat;
    }

    // Implicit casts to long when performing any non assignment binary operation on a long and any
    // non-long type.
    {
      tlong = flong * fint;
      tlong = flong >> fint; // Does not cast to long on right hand side.
    }

    // Implicit casts to long when performing the PLUS binary operation on a long and any non-long
    // type.
    {
      tlong = flong + fshort;
      tlong = flong + flong;
    }

    // Implicit casts to long when passing into a long parameter slot.
    {
      compareLong(fint);
      new LongImplicitCasts(fint);
      compareLong(flong);
      new LongImplicitCasts(flong);
    }

    // Bit shift operations should coerce the right hand side to int (NOT long).
    {
      tlong = flong << flong;
      tlong <<= flong;
    }
  }
}
