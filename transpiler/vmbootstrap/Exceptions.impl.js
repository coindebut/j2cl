/**
 * Impl hand rolled.
 */
goog.module('vmbootstrap.Exceptions$impl');

let Throwable = goog.require('gen.java.lang.Throwable$impl');
let AutoClosable = goog.require('gen.java.lang.AutoCloseable$impl');

/**
 * Provides helper methods.
 */
class Exceptions {
  /**
   * A try with resource block uses safeClose to close resources that have been
   * opened.  If an exception occurred during the resource initialization
   * or in the try block, it is passed in here so that we can add suppressed
   * exceptions to it if the resource fails to close.
   *
   * @param {AutoClosable} resource
   * @param {Throwable} currentException
   */
  static safeClose(resource, currentException) {
    if (resource == null) {
      return currentException;
    }
    try {
      resource.m_close();
    } catch (e) {
      if (currentException == null) {
        return e;
      }
      currentException.m_addSuppressed__java_lang_Throwable(e);
    }
    return currentException;
  }
};


/**
 * Exported class.
 */
exports = Exceptions;