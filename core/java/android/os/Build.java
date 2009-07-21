/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.os;

/**
 * Information about the current build, extracted from system properties.
 */
public class Build {
    /** Value used for when a build property is unknown. */
    private static final String UNKNOWN = "unknown";

    /** Either a changelist number, or a label like "M4-rc20". */
    public static final String ID = getString("ro.build.id");

    /** A build ID string meant for displaying to the user */
    public static final String DISPLAY = getString("ro.build.display.id");

    /** The name of the overall product. */
    public static final String PRODUCT = getString("ro.product.name");

    /** The name of the industrial design. */
    public static final String DEVICE = getString("ro.product.device");

    /** The name of the underlying board, like "goldfish". */
    public static final String BOARD = getString("ro.product.board");

    /** The name of the instruction set (CPU type + ABI convention) of native code. */
    public static final String CPU_ABI = getString("ro.product.cpu.abi");

    /** The manufacturer of the product/hardware. */
    public static final String MANUFACTURER = getString("ro.product.manufacturer");

    /** The brand (e.g., carrier) the software is customized for, if any. */
    public static final String BRAND = getString("ro.product.brand");

    /** The end-user-visible name for the end product. */
    public static final String MODEL = getString("ro.product.model");

    /** Various version strings. */
    public static class VERSION {
        /**
         * The internal value used by the underlying source control to
         * represent this build.  E.g., a perforce changelist number
         * or a git hash.
         */
        public static final String INCREMENTAL = getString("ro.build.version.incremental");

        /**
         * The user-visible version string.  E.g., "1.0" or "3.4b5".
         */
        public static final String RELEASE = getString("ro.build.version.release");

        /**
         * The user-visible SDK version of the framework in its raw String
         * representation; use {@link #SDK_INT} instead.
         * 
         * @deprecated Use {@link #SDK_INT} to easily get this as an integer.
         */
        public static final String SDK = getString("ro.build.version.sdk");

        /**
         * The user-visible SDK version of the framework; its possible
         * values are defined in {@link Build.VERSION_CODES}.
         */
        public static final int SDK_INT = SystemProperties.getInt(
                "ro.build.version.sdk", 0);

        /**
         * The current development codename, or the string "REL" if this is
         * a release build.
         */
        public static final String CODENAME = getString("ro.build.version.codename");
    }

    /**
     * Enumeration of the currently known SDK version codes.  These are the
     * values that can be found in {@link VERSION#SDK}.  Version numbers
     * increment monotonically with each official platform release.
     */
    public static class VERSION_CODES {
        /**
         * Magic version number for a current development build, which has
         * not yet turned into an official release.
         */
        public static final int CUR_DEVELOPMENT = 10000;
        
        /**
         * October 2008: The original, first, version of Android.  Yay!
         */
        public static final int BASE = 1;
        /**
         * February 2009: First Android update, officially called 1.1.
         */
        public static final int BASE_1_1 = 2;
        /**
         * May 2009: Android 1.5.
         */
        public static final int CUPCAKE = 3;
        /**
         * Current work on "Donut" development branch.
         * 
         * <p>Applications targeting this or a later release will get these
         * new changes in behavior:</p>
         * <ul>
         * <li> They must explicitly request the
         * {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE} permission to be
         * able to modify the contents of the SD card.  (Apps targeting
         * earlier versions will always request the permission.)
         * </ul>
         */
        public static final int DONUT = CUR_DEVELOPMENT;
    }
    
    /** The type of build, like "user" or "eng". */
    public static final String TYPE = getString("ro.build.type");

    /** Comma-separated tags describing the build, like "unsigned,debug". */
    public static final String TAGS = getString("ro.build.tags");

    /** A string that uniquely identifies this build.  Do not attempt to parse this value. */
    public static final String FINGERPRINT = getString("ro.build.fingerprint");

    // The following properties only make sense for internal engineering builds.
    public static final long TIME = getLong("ro.build.date.utc") * 1000;
    public static final String USER = getString("ro.build.user");
    public static final String HOST = getString("ro.build.host");

    private static String getString(String property) {
        return SystemProperties.get(property, UNKNOWN);
    }

    private static long getLong(String property) {
        try {
            return Long.parseLong(SystemProperties.get(property));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
