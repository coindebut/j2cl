/*
 * Copyright 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.j2cl.ast;

import com.google.auto.value.AutoValue;
import com.google.common.collect.Interner;
import com.google.common.collect.Interners;

import javax.annotation.Nullable;

/**
 * Encapsulates JsInterop information.
 */
@AutoValue
public abstract class JsInfo {
  public abstract JsMemberType getJsMemberType();

  @Nullable
  public abstract String getJsName();

  @Nullable
  public abstract String getJsNamespace();

  public abstract boolean isJsOverlay();

  /**
   * Not a JS member.
   */
  public static final JsInfo NONE = create(JsMemberType.NONE, null, null, false);

  private static Interner<JsInfo> interner;

  /**
   * Creates an instance of an AutoValue generated JsInfo which uses Interners
   * to share identical instances of JsInfos.
   */
  public static JsInfo create(
      JsMemberType jsMemberType, String jsName, String jsNamespace, boolean isJsOverlay) {
    return getInterner()
        .intern(new AutoValue_JsInfo(jsMemberType, jsName, jsNamespace, isJsOverlay));
  }

  private static Interner<JsInfo> getInterner() {
    if (interner == null) {
      interner = Interners.newWeakInterner();
    }
    return interner;
  }

  public boolean isNone() {
    return this == NONE;
  }
}