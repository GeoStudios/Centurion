/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package jdk.crypto.cryptoki.share.classes.sun.security.pkcs11.wrapper;

















/**
 * This interface holds constants of the PKCS#11 v3.00 standard.
 * This is mainly the content of the 'pkcs11t.h' header file.
 *
 * Mapping of primitiv data types to Java types:
 * <pre>
 *   TRUE .......................................... true
 *   FALSE ......................................... false
 *   CK_BYTE ....................................... byte
 *   CK_CHAR ....................................... char
 *   CK_UTF8CHAR ................................... char
 *   CK_BBOOL ...................................... boolean
 *   CK_ULONG ...................................... long
 *   CK_LONG ....................................... long
 *   CK_FLAGS ...................................... long
 *   CK_BYTE_PTR ................................... byte[]
 *   CK_CHAR_PTR ................................... char[]
 *   CK_UTF8CHAR_PTR ............................... char[]
 *   CK_ULONG_PTR .................................. long[]
 *   CK_VOID_PTR ................................... Object[]
 *   CK_NOTIFICATION ............................... long
 *   CK_SLOT_ID .................................... long
 *   CK_SESSION_HANDLE ............................. long
 *   CK_USER_TYPE .................................. long
 *   CK_STATE ...................................... long
 *   CK_OBJECT_HANDLE .............................. long
 *   CK_OBJECT_CLASS ............................... long
 *   CK_HW_FEATURE_TYPE ............................ long
 *   CK_KEY_TYPE ................................... long
 *   CK_CERTIFICATE_TYPE ........................... long
 *   CK_ATTRIBUTE_TYPE ............................. long
 *   CK_MECHANISM_TYPE ............................. long
 *   CK_RV ......................................... long
 *   CK_RSA_PKCS_MGF_TYPE .......................... long
 *   CK_RSA_PKCS_OAEP_SOURCE_TYPE .................. long
 *   CK_EC_KDF_TYPE ................................ long
 *   CK_X9_42_DH_KDF_TYPE .......................... long
 *   CK_RC2_PARAMS ................................. long
 *   CK_MAC_GENERAL_PARAMS ......................... long
 *   CK_EXTRACT_PARAMS ............................. long
 *   CK_PKCS5_PBKD2_PSEUDO_RANDOM_FUNCTION_TYPE .... long
 *   CK_PKCS5_PBKDF2_SALT_SOURCE_TYPE .............. long
 *   CK_OTP_PARAM_TYPE / CK_PARAM_TYPE ............. long
 *   CK_GENERATOR_FUNCTION ......................... long
 *   CK_JAVA_MIDP_SECURITY_DOMAIN .................. long
 *   CK_CERTIFICATE_CATEGORY ....................... long
 *   CK_PROFILE_ID ................................. long
 *   CK_PRF_DATA_TYPE .............................. long
 *   CK_SP800_108_DKM_LENGTH_METHOD ................ long
 *   CK_X3DH_KDF_TYPE .............................. long
 *   CK_X2RATCHET_KDF_TYPE ......................... long
 *   CK_XEDDSA_HASH_TYPE ........................... long
 * </pre>
 *
 * @invariants
 */
public interface PKCS11Constants {

    boolean TRUE = true;

    boolean FALSE = false;

    Object NULL_PTR = null;

    /* some special values for certain CK_ULONG variables */

    // Cryptoki defines CK_UNAVAILABLE_INFORMATION as (~0UL)
    // This means it is 0xffffffff in ILP32/LLP64 but 0xffffffffffffffff in LP64.
    // To avoid these differences on the Java side, the native code treats
    // CK_UNAVAILABLE_INFORMATION specially and always returns (long)-1 for it.
    // See ckULongSpecialToJLong() in pkcs11wrapper.h
    long CK_UNAVAILABLE_INFORMATION = -1;
    long CK_EFFECTIVELY_INFINITE = 0L;

    /* The following value is always invalid if used as a session */
    /* handle or object handle */
    long CK_INVALID_HANDLE = 0L;

    /* CK_NOTIFICATION enumerates the types of notifications that
     * Cryptoki provides to an application */
    /* CK_NOTIFICATION has been changed from an enum to a CK_ULONG
     * for v2.0 */
    long CKN_SURRENDER = 0L;

    /* flags: bit flags that provide capabilities of the slot
     *      Bit Flag              Mask        Meaning
     */
    long CKF_TOKEN_PRESENT = 0x00000001L;
    long CKF_REMOVABLE_DEVICE = 0x00000002L;
    long CKF_HW_SLOT = 0x00000004L;

    /* The flags parameter is defined as follows:
     *      Bit Flag                    Mask        Meaning
     */
    /* has random # generator */
    long  CKF_RNG                     = 0x00000001L;

    /* token is write-protected */
    long  CKF_WRITE_PROTECTED         = 0x00000002L;

    /* user must login */
    long  CKF_LOGIN_REQUIRED          = 0x00000004L;

    /* normal user's PIN is set */
    long  CKF_USER_PIN_INITIALIZED    = 0x00000008L;

    long  CKF_RESTORE_KEY_NOT_NEEDED  = 0x00000020L;

    long  CKF_CLOCK_ON_TOKEN          = 0x00000040L;

    long  CKF_PROTECTED_AUTHENTICATION_PATH = 0x00000100L;

    long  CKF_DUAL_CRYPTO_OPERATIONS  = 0x00000200L;

    long  CKF_TOKEN_INITIALIZED       = 0x00000400L;

    long  CKF_SECONDARY_AUTHENTICATION  = 0x00000800L;

    long  CKF_USER_PIN_COUNT_LOW       = 0x00010000L;

    long  CKF_USER_PIN_FINAL_TRY       = 0x00020000L;

    long  CKF_USER_PIN_LOCKED          = 0x00040000L;

    long  CKF_USER_PIN_TO_BE_CHANGED   = 0x00080000L;

    long  CKF_SO_PIN_COUNT_LOW         = 0x00100000L;

    long  CKF_SO_PIN_FINAL_TRY         = 0x00200000L;

    long  CKF_SO_PIN_LOCKED            = 0x00400000L;

    long  CKF_SO_PIN_TO_BE_CHANGED     = 0x00800000L;

    /* Security Officer */
    long CKU_SO = 0L;
    /* Normal user */
    long CKU_USER = 1L;

    /* CK_STATE enumerates the session states */
    long  CKS_RO_PUBLIC_SESSION = 0L;
    long  CKS_RO_USER_FUNCTIONS = 1L;
    long  CKS_RW_PUBLIC_SESSION = 2L;
    long  CKS_RW_USER_FUNCTIONS = 3L;
    long  CKS_RW_SO_FUNCTIONS   = 4L;

    /* The flags are defined in the following table:
     *      Bit Flag                Mask        Meaning
     */
    /* session is r/w */
    long  CKF_RW_SESSION        = 0x00000002L;
    /* no parallel */
    long  CKF_SERIAL_SESSION    = 0x00000004L;


    /* The following classes of objects are defined: */
    long  CKO_DATA              = 0x00000000L;
    long  CKO_CERTIFICATE       = 0x00000001L;
    long  CKO_PUBLIC_KEY        = 0x00000002L;
    long  CKO_PRIVATE_KEY       = 0x00000003L;
    long  CKO_SECRET_KEY        = 0x00000004L;
    long  CKO_HW_FEATURE        = 0x00000005L;
    long  CKO_DOMAIN_PARAMETERS = 0x00000006L;
    long  CKO_MECHANISM         = 0x00000007L;
    long  CKO_OTP_KEY           = 0x00000008L;
    long  CKO_PROFILE           = 0x00000009L;

    long  CKO_VENDOR_DEFINED    = 0x80000000L;

    // pseudo object class ANY (for template manager)
    long  PCKO_ANY              = 0x7FFFFF23L;

    /* Uncomment when actually used
    // Profile ID's
    public static final long  CKP_INVALID_ID                = 0x00000000L;
    public static final long  CKP_BASELINE_PROVIDER         = 0x00000001L;
    public static final long  CKP_EXTENDED_PROVIDER         = 0x00000002L;
    public static final long  CKP_AUTHENTICATION_TOKEN      = 0x00000003L;
    public static final long  CKP_PUBLIC_CERTIFICATES_TOKEN = 0x00000004L;
    public static final long  CKP_VENDOR_DEFINED            = 0x80000000L;

    // The following hardware feature types are defined
    public static final long  CKH_MONOTONIC_COUNTER = 0x00000001L;
    public static final long  CKH_CLOCK             = 0x00000002L;
    public static final long  CKH_USER_INTERFACE    = 0x00000003L;
    public static final long  CKH_VENDOR_DEFINED    = 0x80000000L;
    */

    /* the following key types are defined: */
    long  CKK_RSA                  = 0x00000000L;
    long  CKK_DSA                  = 0x00000001L;
    long  CKK_DH                   = 0x00000002L;
    long  CKK_ECDSA /*deprecated*/ = 0x00000003L;
    long  CKK_EC                   = 0x00000003L;
    long  CKK_X9_42_DH             = 0x00000004L;
    long  CKK_KEA                  = 0x00000005L;
    long  CKK_GENERIC_SECRET       = 0x00000010L;
    long  CKK_RC2                  = 0x00000011L;
    long  CKK_RC4                  = 0x00000012L;
    long  CKK_DES                  = 0x00000013L;
    long  CKK_DES2                 = 0x00000014L;
    long  CKK_DES3                 = 0x00000015L;

    long  CKK_CAST                 = 0x00000016L;
    long  CKK_CAST3                = 0x00000017L;
    long  CKK_CAST5 /*deprecated*/ = 0x00000018L;
    long  CKK_CAST128              = 0x00000018L;
    long  CKK_RC5                  = 0x00000019L;
    long  CKK_IDEA                 = 0x0000001AL;
    long  CKK_SKIPJACK             = 0x0000001BL;
    long  CKK_BATON                = 0x0000001CL;
    long  CKK_JUNIPER              = 0x0000001DL;
    long  CKK_CDMF                 = 0x0000001EL;
    long  CKK_AES                  = 0x0000001FL;
    long  CKK_BLOWFISH             = 0x00000020L;
    long  CKK_TWOFISH              = 0x00000021L;
    long  CKK_SECURID              = 0x00000022L;
    long  CKK_HOTP                 = 0x00000023L;
    long  CKK_ACTI                 = 0x00000024L;
    long  CKK_CAMELLIA             = 0x00000025L;
    long  CKK_ARIA                 = 0x00000026L;

    long  CKK_MD5_HMAC             = 0x00000027L;
    long  CKK_SHA_1_HMAC           = 0x00000028L;
    long  CKK_RIPEMD128_HMAC       = 0x00000029L;
    long  CKK_RIPEMD160_HMAC       = 0x0000002AL;
    long  CKK_SHA256_HMAC          = 0x0000002BL;
    long  CKK_SHA384_HMAC          = 0x0000002CL;
    long  CKK_SHA512_HMAC          = 0x0000002DL;
    long  CKK_SHA224_HMAC          = 0x0000002EL;

    long  CKK_SEED                 = 0x0000002FL;
    long  CKK_GOSTR3410            = 0x00000030L;
    long  CKK_GOSTR3411            = 0x00000031L;
    long  CKK_GOST28147            = 0x00000032L;
    long  CKK_CHACHA20             = 0x00000033L;
    long  CKK_POLY1305             = 0x00000034L;
    long  CKK_AES_XTS              = 0x00000035L;

    long  CKK_SHA3_224_HMAC        = 0x00000036L;
    long  CKK_SHA3_256_HMAC        = 0x00000037L;
    long  CKK_SHA3_384_HMAC        = 0x00000038L;
    long  CKK_SHA3_512_HMAC        = 0x00000039L;

    long  CKK_BLAKE2B_160_HMAC     = 0x0000003aL;
    long  CKK_BLAKE2B_256_HMAC     = 0x0000003bL;
    long  CKK_BLAKE2B_384_HMAC     = 0x0000003cL;
    long  CKK_BLAKE2B_512_HMAC     = 0x0000003dL;
    long  CKK_SALSA20              = 0x0000003eL;
    long  CKK_X2RATCHET            = 0x0000003fL;
    long  CKK_EC_EDWARDS           = 0x00000040L;
    long  CKK_EC_MONTGOMERY        = 0x00000041L;
    long  CKK_HKDF                 = 0x00000042L;

    long  CKK_SHA512_224_HMAC      = 0x00000043L;
    long  CKK_SHA512_256_HMAC      = 0x00000044L;
    long  CKK_SHA512_T_HMAC        = 0x00000045L;

    long  CKK_VENDOR_DEFINED       = 0x80000000L;

    // pseudo key type ANY (for template manager)
    long  PCKK_ANY                 = 0x7FFFFF22L;

    long  PCKK_HMAC                = 0x7FFFFF23L;
    long  PCKK_SSLMAC              = 0x7FFFFF24L;
    long  PCKK_TLSPREMASTER        = 0x7FFFFF25L;
    long  PCKK_TLSRSAPREMASTER     = 0x7FFFFF26L;
    long  PCKK_TLSMASTER           = 0x7FFFFF27L;

    /* Uncomment when actually used
    public static final long  CK_CERTIFICATE_CATEGORY_UNSPECIFIED   = 0L;
    public static final long  CK_CERTIFICATE_CATEGORY_TOKEN_USER    = 1L;
    public static final long  CK_CERTIFICATE_CATEGORY_AUTHORITY     = 2L;
    public static final long  CK_CERTIFICATE_CATEGORY_OTHER_ENTITY  = 3L;

    public static final long  CK_SECURITY_DOMAIN_UNSPECIFIED   = 0L;
    public static final long  CK_SECURITY_DOMAIN_MANUFACTURER  = 1L;
    public static final long  CK_SECURITY_DOMAIN_OPERATOR      = 2L;
    public static final long  CK_SECURITY_DOMAIN_THIRD_PARTY   = 3L;
    */

    /* The following certificate types are defined: */
    long  CKC_X_509                = 0x00000000L;
    long  CKC_X_509_ATTR_CERT      = 0x00000001L;
    long  CKC_WTLS                 = 0x00000002L;
    long  CKC_VENDOR_DEFINED       = 0x80000000L;

    /* The CKF_ARRAY_ATTRIBUTE flag identifies an attribute which
     * consists of an array of values.
     */
    long  CKF_ARRAY_ATTRIBUTE      = 0x40000000L;

    /* Uncomment when actually used
    public static final long  CK_OTP_FORMAT_DECIMAL         = 0L;
    public static final long  CK_OTP_FORMAT_HEXADECIMAL     = 1L;
    public static final long  CK_OTP_FORMAT_ALPHANUMERIC    = 2L;
    public static final long  CK_OTP_FORMAT_BINARY          = 3L;

    public static final long  CK_OTP_PARAM_IGNORED          = 0L;
    public static final long  CK_OTP_PARAM_OPTIONAL         = 1L;
    public static final long  CK_OTP_PARAM_MANDATORY        = 2L;
    */

    /* The following attribute types are defined: */
    long  CKA_CLASS              = 0x00000000L;
    long  CKA_TOKEN              = 0x00000001L;
    long  CKA_PRIVATE            = 0x00000002L;
    long  CKA_LABEL              = 0x00000003L;
    long  CKA_UNIQUE_ID          = 0x00000004L;
    long  CKA_APPLICATION        = 0x00000010L;
    long  CKA_VALUE              = 0x00000011L;
    long  CKA_OBJECT_ID          = 0x00000012L;
    long  CKA_CERTIFICATE_TYPE   = 0x00000080L;
    long  CKA_ISSUER             = 0x00000081L;
    long  CKA_SERIAL_NUMBER      = 0x00000082L;
    long  CKA_AC_ISSUER          = 0x00000083L;
    long  CKA_OWNER              = 0x00000084L;
    long  CKA_ATTR_TYPES         = 0x00000085L;
    long  CKA_TRUSTED            = 0x00000086L;
    long  CKA_CERTIFICATE_CATEGORY
                                                     = 0x00000087L;
    long  CKA_JAVA_MIDP_SECURITY_DOMAIN
                                                     = 0x00000088L;
    long  CKA_URL                = 0x00000089L;
    long  CKA_HASH_OF_SUBJECT_PUBLIC_KEY
                                                     = 0x0000008AL;
    long  CKA_HASH_OF_ISSUER_PUBLIC_KEY
                                                     = 0x0000008BL;
    long  CKA_NAME_HASH_ALGORITHM
                                                     = 0x0000008CL;
    long  CKA_CHECK_VALUE        = 0x00000090L;

    long  CKA_KEY_TYPE           = 0x00000100L;
    long  CKA_SUBJECT            = 0x00000101L;
    long  CKA_ID                 = 0x00000102L;
    long  CKA_SENSITIVE          = 0x00000103L;
    long  CKA_ENCRYPT            = 0x00000104L;
    long  CKA_DECRYPT            = 0x00000105L;
    long  CKA_WRAP               = 0x00000106L;
    long  CKA_UNWRAP             = 0x00000107L;
    long  CKA_SIGN               = 0x00000108L;
    long  CKA_SIGN_RECOVER       = 0x00000109L;
    long  CKA_VERIFY             = 0x0000010AL;
    long  CKA_VERIFY_RECOVER     = 0x0000010BL;
    long  CKA_DERIVE             = 0x0000010CL;
    long  CKA_START_DATE         = 0x00000110L;
    long  CKA_END_DATE           = 0x00000111L;
    long  CKA_MODULUS            = 0x00000120L;
    long  CKA_MODULUS_BITS       = 0x00000121L;
    long  CKA_PUBLIC_EXPONENT    = 0x00000122L;
    long  CKA_PRIVATE_EXPONENT   = 0x00000123L;
    long  CKA_PRIME_1            = 0x00000124L;
    long  CKA_PRIME_2            = 0x00000125L;
    long  CKA_EXPONENT_1         = 0x00000126L;
    long  CKA_EXPONENT_2         = 0x00000127L;
    long  CKA_COEFFICIENT        = 0x00000128L;
    long  CKA_PUBLIC_KEY_INFO    = 0x00000129L;
    long  CKA_PRIME              = 0x00000130L;
    long  CKA_SUBPRIME           = 0x00000131L;
    long  CKA_BASE               = 0x00000132L;

    long  CKA_PRIME_BITS         = 0x00000133L;
    long  CKA_SUB_PRIME_BITS     = 0x00000134L;

    long  CKA_VALUE_BITS         = 0x00000160L;
    long  CKA_VALUE_LEN          = 0x00000161L;
    long  CKA_EXTRACTABLE        = 0x00000162L;
    long  CKA_LOCAL              = 0x00000163L;
    long  CKA_NEVER_EXTRACTABLE  = 0x00000164L;
    long  CKA_ALWAYS_SENSITIVE   = 0x00000165L;
    long  CKA_KEY_GEN_MECHANISM  = 0x00000166L;

    long  CKA_MODIFIABLE         = 0x00000170L;
    long  CKA_COPYABLE           = 0x00000171L;
    long  CKA_DESTROYABLE        = 0x00000172L;

    long  CKA_ECDSA_PARAMS /*deprecated*/  = 0x00000180L;
    long  CKA_EC_PARAMS                    = 0x00000180L;
    long  CKA_EC_POINT                     = 0x00000181L;

    long  CKA_SECONDARY_AUTH /*deprecated*/= 0x00000200L;
    long  CKA_AUTH_PIN_FLAGS /*deprecated*/= 0x00000201L;
    long  CKA_ALWAYS_AUTHENTICATE          = 0x00000202L;
    long  CKA_WRAP_WITH_TRUSTED  = 0x00000210L;
    long  CKA_WRAP_TEMPLATE      = (CKF_ARRAY_ATTRIBUTE|0x00000211L);
    long  CKA_UNWRAP_TEMPLATE    = (CKF_ARRAY_ATTRIBUTE|0x00000212L);
    long  CKA_DERIVE_TEMPLATE    = (CKF_ARRAY_ATTRIBUTE|0x00000213L);

    long  CKA_OTP_FORMAT         = 0x00000220L;
    long  CKA_OTP_LENGTH         = 0x00000221L;
    long  CKA_OTP_TIME_INTERVAL  = 0x00000222L;
    long  CKA_OTP_USER_FRIENDLY_MODE       = 0x00000223L;
    long  CKA_OTP_CHALLENGE_REQUIREMENT    = 0x00000224L;
    long  CKA_OTP_TIME_REQUIREMENT         = 0x00000225L;
    long  CKA_OTP_COUNTER_REQUIREMENT      = 0x00000226L;
    long  CKA_OTP_PIN_REQUIREMENT          = 0x00000227L;
    long  CKA_OTP_COUNTER        = 0x0000022EL;
    long  CKA_OTP_TIME           = 0x0000022FL;
    long  CKA_OTP_USER_IDENTIFIER          = 0x0000022AL;
    long  CKA_OTP_SERVICE_IDENTIFIER       = 0x0000022BL;
    long  CKA_OTP_SERVICE_LOGO   = 0x0000022CL;
    long  CKA_OTP_SERVICE_LOGO_TYPE        = 0x0000022DL;

    long  CKA_GOSTR3410_PARAMS   = 0x00000250L;
    long  CKA_GOSTR3411_PARAMS   = 0x00000251L;
    long  CKA_GOST28147_PARAMS   = 0x00000252L;

    long  CKA_HW_FEATURE_TYPE    = 0x00000300L;
    long  CKA_RESET_ON_INIT      = 0x00000301L;
    long  CKA_HAS_RESET          = 0x00000302L;

    long  CKA_PIXEL_X            = 0x00000400L;
    long  CKA_PIXEL_Y            = 0x00000401L;
    long  CKA_RESOLUTION         = 0x00000402L;
    long  CKA_CHAR_ROWS          = 0x00000403L;
    long  CKA_CHAR_COLUMNS       = 0x00000404L;
    long  CKA_COLOR              = 0x00000405L;
    long  CKA_BITS_PER_PIXEL     = 0x00000406L;
    long  CKA_CHAR_SETS          = 0x00000480L;
    long  CKA_ENCODING_METHODS   = 0x00000481L;
    long  CKA_MIME_TYPES         = 0x00000482L;
    long  CKA_MECHANISM_TYPE     = 0x00000500L;
    long  CKA_REQUIRED_CMS_ATTRIBUTES      = 0x00000501L;
    long  CKA_DEFAULT_CMS_ATTRIBUTES       = 0x00000502L;
    long  CKA_SUPPORTED_CMS_ATTRIBUTES     = 0x00000503L;
    long  CKA_ALLOWED_MECHANISMS = (CKF_ARRAY_ATTRIBUTE|0x00000600L);

    long  CKA_PROFILE_ID                   = 0x00000601L;
    long  CKA_X2RATCHET_BAG                = 0x00000602L;
    long  CKA_X2RATCHET_BAGSIZE            = 0x00000603L;
    long  CKA_X2RATCHET_BOBS1STMSG         = 0x00000604L;
    long  CKA_X2RATCHET_CKR                = 0x00000605L;
    long  CKA_X2RATCHET_CKS                = 0x00000606L;
    long  CKA_X2RATCHET_DHP                = 0x00000607L;
    long  CKA_X2RATCHET_DHR                = 0x00000608L;
    long  CKA_X2RATCHET_DHS                = 0x00000609L;
    long  CKA_X2RATCHET_HKR                = 0x0000060aL;
    long  CKA_X2RATCHET_HKS                = 0x0000060bL;
    long  CKA_X2RATCHET_ISALICE            = 0x0000060cL;
    long  CKA_X2RATCHET_NHKR               = 0x0000060dL;
    long  CKA_X2RATCHET_NHKS               = 0x0000060eL;
    long  CKA_X2RATCHET_NR                 = 0x0000060fL;
    long  CKA_X2RATCHET_NS                 = 0x00000610L;
    long  CKA_X2RATCHET_PNS                = 0x00000611L;
    long  CKA_X2RATCHET_RK                 = 0x00000612L;

    long  CKA_VENDOR_DEFINED     = 0x80000000L;

    /* the following mechanism types are defined: */
    long  CKM_RSA_PKCS_KEY_PAIR_GEN      = 0x00000000L;
    long  CKM_RSA_PKCS                   = 0x00000001L;
    long  CKM_RSA_9796                   = 0x00000002L;
    long  CKM_RSA_X_509                  = 0x00000003L;

    long  CKM_MD2_RSA_PKCS               = 0x00000004L;
    long  CKM_MD5_RSA_PKCS               = 0x00000005L;
    long  CKM_SHA1_RSA_PKCS              = 0x00000006L;

    long  CKM_RIPEMD128_RSA_PKCS         = 0x00000007L;
    long  CKM_RIPEMD160_RSA_PKCS         = 0x00000008L;
    long  CKM_RSA_PKCS_OAEP              = 0x00000009L;

    long  CKM_RSA_X9_31_KEY_PAIR_GEN     = 0x0000000AL;
    long  CKM_RSA_X9_31                  = 0x0000000BL;
    long  CKM_SHA1_RSA_X9_31             = 0x0000000CL;
    long  CKM_RSA_PKCS_PSS               = 0x0000000DL;
    long  CKM_SHA1_RSA_PKCS_PSS          = 0x0000000EL;

    long  CKM_DSA_KEY_PAIR_GEN           = 0x00000010L;
    long  CKM_DSA                        = 0x00000011L;
    long  CKM_DSA_SHA1                   = 0x00000012L;
    long  CKM_DSA_SHA224                 = 0x00000013L;
    long  CKM_DSA_SHA256                 = 0x00000014L;
    long  CKM_DSA_SHA384                 = 0x00000015L;
    long  CKM_DSA_SHA512                 = 0x00000016L;
    long  CKM_DSA_SHA3_224               = 0x00000018L;
    long  CKM_DSA_SHA3_256               = 0x00000019L;
    long  CKM_DSA_SHA3_384               = 0x0000001AL;
    long  CKM_DSA_SHA3_512               = 0x0000001BL;

    long  CKM_DH_PKCS_KEY_PAIR_GEN       = 0x00000020L;
    long  CKM_DH_PKCS_DERIVE             = 0x00000021L;

    long  CKM_X9_42_DH_KEY_PAIR_GEN      = 0x00000030L;
    long  CKM_X9_42_DH_DERIVE            = 0x00000031L;
    long  CKM_X9_42_DH_HYBRID_DERIVE     = 0x00000032L;
    long  CKM_X9_42_MQV_DERIVE           = 0x00000033L;

    long  CKM_SHA256_RSA_PKCS            = 0x00000040L;
    long  CKM_SHA384_RSA_PKCS            = 0x00000041L;
    long  CKM_SHA512_RSA_PKCS            = 0x00000042L;
    long  CKM_SHA256_RSA_PKCS_PSS        = 0x00000043L;
    long  CKM_SHA384_RSA_PKCS_PSS        = 0x00000044L;
    long  CKM_SHA512_RSA_PKCS_PSS        = 0x00000045L;

    long  CKM_SHA224_RSA_PKCS            = 0x00000046L;
    long  CKM_SHA224_RSA_PKCS_PSS        = 0x00000047L;

    long  CKM_SHA512_224                 = 0x00000048L;
    long  CKM_SHA512_224_HMAC            = 0x00000049L;
    long  CKM_SHA512_224_HMAC_GENERAL    = 0x0000004AL;
    long  CKM_SHA512_224_KEY_DERIVATION  = 0x0000004BL;
    long  CKM_SHA512_256                 = 0x0000004CL;
    long  CKM_SHA512_256_HMAC            = 0x0000004DL;
    long  CKM_SHA512_256_HMAC_GENERAL    = 0x0000004EL;
    long  CKM_SHA512_256_KEY_DERIVATION  = 0x0000004FL;

    long  CKM_SHA512_T                   = 0x00000050L;
    long  CKM_SHA512_T_HMAC              = 0x00000051L;
    long  CKM_SHA512_T_HMAC_GENERAL      = 0x00000052L;
    long  CKM_SHA512_T_KEY_DERIVATION    = 0x00000053L;

    long  CKM_SHA3_256_RSA_PKCS          = 0x00000060L;
    long  CKM_SHA3_384_RSA_PKCS          = 0x00000061L;
    long  CKM_SHA3_512_RSA_PKCS          = 0x00000062L;
    long  CKM_SHA3_256_RSA_PKCS_PSS      = 0x00000063L;
    long  CKM_SHA3_384_RSA_PKCS_PSS      = 0x00000064L;
    long  CKM_SHA3_512_RSA_PKCS_PSS      = 0x00000065L;
    long  CKM_SHA3_224_RSA_PKCS          = 0x00000066L;
    long  CKM_SHA3_224_RSA_PKCS_PSS      = 0x00000067L;

    long  CKM_RC2_KEY_GEN                = 0x00000100L;
    long  CKM_RC2_ECB                    = 0x00000101L;
    long  CKM_RC2_CBC                    = 0x00000102L;
    long  CKM_RC2_MAC                    = 0x00000103L;

    long  CKM_RC2_MAC_GENERAL            = 0x00000104L;
    long  CKM_RC2_CBC_PAD                = 0x00000105L;

    long  CKM_RC4_KEY_GEN                = 0x00000110L;
    long  CKM_RC4                        = 0x00000111L;
    long  CKM_DES_KEY_GEN                = 0x00000120L;
    long  CKM_DES_ECB                    = 0x00000121L;
    long  CKM_DES_CBC                    = 0x00000122L;
    long  CKM_DES_MAC                    = 0x00000123L;

    long  CKM_DES_MAC_GENERAL            = 0x00000124L;
    long  CKM_DES_CBC_PAD                = 0x00000125L;

    long  CKM_DES2_KEY_GEN               = 0x00000130L;
    long  CKM_DES3_KEY_GEN               = 0x00000131L;
    long  CKM_DES3_ECB                   = 0x00000132L;
    long  CKM_DES3_CBC                   = 0x00000133L;
    long  CKM_DES3_MAC                   = 0x00000134L;

    long  CKM_DES3_MAC_GENERAL           = 0x00000135L;
    long  CKM_DES3_CBC_PAD               = 0x00000136L;
    long  CKM_DES3_CMAC_GENERAL          = 0x00000137L;
    long  CKM_DES3_CMAC                  = 0x00000138L;
    long  CKM_CDMF_KEY_GEN               = 0x00000140L;
    long  CKM_CDMF_ECB                   = 0x00000141L;
    long  CKM_CDMF_CBC                   = 0x00000142L;
    long  CKM_CDMF_MAC                   = 0x00000143L;
    long  CKM_CDMF_MAC_GENERAL           = 0x00000144L;
    long  CKM_CDMF_CBC_PAD               = 0x00000145L;

    long  CKM_DES_OFB64                  = 0x00000150L;
    long  CKM_DES_OFB8                   = 0x00000151L;
    long  CKM_DES_CFB64                  = 0x00000152L;
    long  CKM_DES_CFB8                   = 0x00000153L;

    long  CKM_MD2                        = 0x00000200L;

    long  CKM_MD2_HMAC                   = 0x00000201L;
    long  CKM_MD2_HMAC_GENERAL           = 0x00000202L;

    long  CKM_MD5                        = 0x00000210L;

    long  CKM_MD5_HMAC                   = 0x00000211L;
    long  CKM_MD5_HMAC_GENERAL           = 0x00000212L;

    long  CKM_SHA_1                      = 0x00000220L;

    long  CKM_SHA_1_HMAC                 = 0x00000221L;
    long  CKM_SHA_1_HMAC_GENERAL         = 0x00000222L;

    long  CKM_RIPEMD128                  = 0x00000230L;
    long  CKM_RIPEMD128_HMAC             = 0x00000231L;
    long  CKM_RIPEMD128_HMAC_GENERAL     = 0x00000232L;
    long  CKM_RIPEMD160                  = 0x00000240L;
    long  CKM_RIPEMD160_HMAC             = 0x00000241L;
    long  CKM_RIPEMD160_HMAC_GENERAL     = 0x00000242L;

    long  CKM_SHA256                     = 0x00000250L;
    long  CKM_SHA256_HMAC                = 0x00000251L;
    long  CKM_SHA256_HMAC_GENERAL        = 0x00000252L;
    long  CKM_SHA224                     = 0x00000255L;
    long  CKM_SHA224_HMAC                = 0x00000256L;
    long  CKM_SHA224_HMAC_GENERAL        = 0x00000257L;
    long  CKM_SHA384                     = 0x00000260L;
    long  CKM_SHA384_HMAC                = 0x00000261L;
    long  CKM_SHA384_HMAC_GENERAL        = 0x00000262L;

    long  CKM_SHA512                     = 0x00000270L;
    long  CKM_SHA512_HMAC                = 0x00000271L;
    long  CKM_SHA512_HMAC_GENERAL        = 0x00000272L;

    long  CKM_SECURID_KEY_GEN            = 0x00000280L;
    long  CKM_SECURID                    = 0x00000282L;
    long  CKM_HOTP_KEY_GEN               = 0x00000290L;
    long  CKM_HOTP                       = 0x00000291L;
    long  CKM_ACTI                       = 0x000002A0L;
    long  CKM_ACTI_KEY_GEN               = 0x000002A1L;

    long  CKM_SHA3_256                   = 0x000002B0L;
    long  CKM_SHA3_256_HMAC              = 0x000002B1L;
    long  CKM_SHA3_256_HMAC_GENERAL      = 0x000002B2L;
    long  CKM_SHA3_256_KEY_GEN           = 0x000002B3L;
    long  CKM_SHA3_224                   = 0x000002B5L;
    long  CKM_SHA3_224_HMAC              = 0x000002B6L;
    long  CKM_SHA3_224_HMAC_GENERAL      = 0x000002B7L;
    long  CKM_SHA3_224_KEY_GEN           = 0x000002B8L;
    long  CKM_SHA3_384                   = 0x000002C0L;
    long  CKM_SHA3_384_HMAC              = 0x000002C1L;
    long  CKM_SHA3_384_HMAC_GENERAL      = 0x000002C2L;
    long  CKM_SHA3_384_KEY_GEN           = 0x000002C3L;
    long  CKM_SHA3_512                   = 0x000002D0L;
    long  CKM_SHA3_512_HMAC              = 0x000002D1L;
    long  CKM_SHA3_512_HMAC_GENERAL      = 0x000002D2L;
    long  CKM_SHA3_512_KEY_GEN           = 0x000002D3L;

    long  CKM_CAST_KEY_GEN               = 0x00000300L;
    long  CKM_CAST_ECB                   = 0x00000301L;
    long  CKM_CAST_CBC                   = 0x00000302L;
    long  CKM_CAST_MAC                   = 0x00000303L;
    long  CKM_CAST_MAC_GENERAL           = 0x00000304L;
    long  CKM_CAST_CBC_PAD               = 0x00000305L;
    long  CKM_CAST3_KEY_GEN              = 0x00000310L;
    long  CKM_CAST3_ECB                  = 0x00000311L;
    long  CKM_CAST3_CBC                  = 0x00000312L;
    long  CKM_CAST3_MAC                  = 0x00000313L;
    long  CKM_CAST3_MAC_GENERAL          = 0x00000314L;
    long  CKM_CAST3_CBC_PAD              = 0x00000315L;
    /* Note that CAST128 and CAST5 are the same algorithm */
    long  CKM_CAST5_KEY_GEN                = 0x00000320L;
    long  CKM_CAST128_KEY_GEN              = 0x00000320L;
    long  CKM_CAST5_ECB                    = 0x00000321L;
    long  CKM_CAST128_ECB                  = 0x00000321L;
    long  CKM_CAST5_CBC /*deprecated*/     = 0x00000322L;
    long  CKM_CAST128_CBC                  = 0x00000322L;
    long  CKM_CAST5_MAC /*deprecated*/     = 0x00000323L;
    long  CKM_CAST128_MAC                  = 0x00000323L;
    long  CKM_CAST5_MAC_GENERAL /*deprecated*/
                                                               = 0x00000324L;
    long  CKM_CAST128_MAC_GENERAL          = 0x00000324L;
    long  CKM_CAST5_CBC_PAD /*deprecated*/ = 0x00000325L;
    long  CKM_CAST128_CBC_PAD              = 0x00000325L;
    long  CKM_RC5_KEY_GEN                = 0x00000330L;
    long  CKM_RC5_ECB                    = 0x00000331L;
    long  CKM_RC5_CBC                    = 0x00000332L;
    long  CKM_RC5_MAC                    = 0x00000333L;
    long  CKM_RC5_MAC_GENERAL            = 0x00000334L;
    long  CKM_RC5_CBC_PAD                = 0x00000335L;
    long  CKM_IDEA_KEY_GEN               = 0x00000340L;
    long  CKM_IDEA_ECB                   = 0x00000341L;
    long  CKM_IDEA_CBC                   = 0x00000342L;
    long  CKM_IDEA_MAC                   = 0x00000343L;
    long  CKM_IDEA_MAC_GENERAL           = 0x00000344L;
    long  CKM_IDEA_CBC_PAD               = 0x00000345L;
    long  CKM_GENERIC_SECRET_KEY_GEN     = 0x00000350L;
    long  CKM_CONCATENATE_BASE_AND_KEY   = 0x00000360L;
    long  CKM_CONCATENATE_BASE_AND_DATA  = 0x00000362L;
    long  CKM_CONCATENATE_DATA_AND_BASE  = 0x00000363L;
    long  CKM_XOR_BASE_AND_DATA          = 0x00000364L;
    long  CKM_EXTRACT_KEY_FROM_KEY       = 0x00000365L;
    long  CKM_SSL3_PRE_MASTER_KEY_GEN    = 0x00000370L;
    long  CKM_SSL3_MASTER_KEY_DERIVE     = 0x00000371L;
    long  CKM_SSL3_KEY_AND_MAC_DERIVE    = 0x00000372L;

    long  CKM_SSL3_MASTER_KEY_DERIVE_DH  = 0x00000373L;
    long  CKM_TLS_PRE_MASTER_KEY_GEN     = 0x00000374L;
    long  CKM_TLS_MASTER_KEY_DERIVE      = 0x00000375L;
    long  CKM_TLS_KEY_AND_MAC_DERIVE     = 0x00000376L;
    long  CKM_TLS_MASTER_KEY_DERIVE_DH   = 0x00000377L;
    long  CKM_TLS_PRF                    = 0x00000378L;

    long  CKM_SSL3_MD5_MAC               = 0x00000380L;
    long  CKM_SSL3_SHA1_MAC              = 0x00000381L;
    long  CKM_MD5_KEY_DERIVATION         = 0x00000390L;
    long  CKM_MD2_KEY_DERIVATION         = 0x00000391L;
    long  CKM_SHA1_KEY_DERIVATION        = 0x00000392L;
    long  CKM_SHA256_KEY_DERIVATION      = 0x00000393L;
    long  CKM_SHA384_KEY_DERIVATION      = 0x00000394L;
    long  CKM_SHA512_KEY_DERIVATION      = 0x00000395L;
    long  CKM_SHA224_KEY_DERIVATION      = 0x00000396L;
    long  CKM_SHA3_256_KEY_DERIVATION    = 0x00000397L;
    long  CKM_SHA3_224_KEY_DERIVATION    = 0x00000398L;
    long  CKM_SHA3_384_KEY_DERIVATION    = 0x00000399L;
    long  CKM_SHA3_512_KEY_DERIVATION    = 0x0000039AL;
    long  CKM_SHAKE_128_KEY_DERIVATION   = 0x0000039BL;
    long  CKM_SHAKE_256_KEY_DERIVATION   = 0x0000039CL;

    long  CKM_PBE_MD2_DES_CBC            = 0x000003A0L;
    long  CKM_PBE_MD5_DES_CBC            = 0x000003A1L;
    long  CKM_PBE_MD5_CAST_CBC           = 0x000003A2L;
    long  CKM_PBE_MD5_CAST3_CBC          = 0x000003A3L;
    long  CKM_PBE_MD5_CAST5_CBC /*deprecated*/
                                                             = 0x000003A4L;
    long  CKM_PBE_MD5_CAST128_CBC        = 0x000003A4L;
    long  CKM_PBE_SHA1_CAST5_CBC /*deprecated*/
                                                             = 0x000003A5L;
    long  CKM_PBE_SHA1_CAST128_CBC       = 0x000003A5L;
    long  CKM_PBE_SHA1_RC4_128           = 0x000003A6L;
    long  CKM_PBE_SHA1_RC4_40            = 0x000003A7L;
    long  CKM_PBE_SHA1_DES3_EDE_CBC      = 0x000003A8L;
    long  CKM_PBE_SHA1_DES2_EDE_CBC      = 0x000003A9L;
    long  CKM_PBE_SHA1_RC2_128_CBC       = 0x000003AAL;
    long  CKM_PBE_SHA1_RC2_40_CBC        = 0x000003ABL;

    long  CKM_PKCS5_PBKD2                = 0x000003B0L;

    long  CKM_PBA_SHA1_WITH_SHA1_HMAC    = 0x000003C0L;

    long  CKM_WTLS_PRE_MASTER_KEY_GEN         = 0x000003D0L;
    long  CKM_WTLS_MASTER_KEY_DERIVE          = 0x000003D1L;
    long  CKM_WTLS_MASTER_KEY_DERIVE_DH_ECC   = 0x000003D2L;
    long  CKM_WTLS_PRF                        = 0x000003D3L;
    long  CKM_WTLS_SERVER_KEY_AND_MAC_DERIVE  = 0x000003D4L;
    long  CKM_WTLS_CLIENT_KEY_AND_MAC_DERIVE  = 0x000003D5L;

    long  CKM_TLS10_MAC_SERVER /*removed in 3.00*/
                                                             = 0x000003D6L;
    long  CKM_TLS10_MAC_CLIENT /*removed in 3.00*/
                                                             = 0x000003D7L;
    long  CKM_TLS12_MAC                  = 0x000003D8L;
    long  CKM_TLS12_KDF                  = 0x000003D9L;
    long  CKM_TLS12_MASTER_KEY_DERIVE    = 0x000003E0L;
    long  CKM_TLS12_KEY_AND_MAC_DERIVE   = 0x000003E1L;
    long  CKM_TLS12_MASTER_KEY_DERIVE_DH = 0x000003E2L;
    long  CKM_TLS12_KEY_SAFE_DERIVE      = 0x000003E3L;
    long  CKM_TLS_MAC                    = 0x000003E4L;
    long  CKM_TLS_KDF                    = 0x000003E5L;

    long  CKM_KEY_WRAP_LYNKS             = 0x00000400L;
    long  CKM_KEY_WRAP_SET_OAEP          = 0x00000401L;

    long  CKM_CMS_SIG                    = 0x00000500L;
    long  CKM_KIP_DERIVE                 = 0x00000510L;
    long  CKM_KIP_WRAP                   = 0x00000511L;
    long  CKM_KIP_MAC                    = 0x00000512L;

    long  CKM_CAMELLIA_KEY_GEN           = 0x00000550L;
    long  CKM_CAMELLIA_ECB               = 0x00000551L;
    long  CKM_CAMELLIA_CBC               = 0x00000552L;
    long  CKM_CAMELLIA_MAC               = 0x00000553L;
    long  CKM_CAMELLIA_MAC_GENERAL       = 0x00000554L;
    long  CKM_CAMELLIA_CBC_PAD           = 0x00000555L;
    long  CKM_CAMELLIA_ECB_ENCRYPT_DATA  = 0x00000556L;
    long  CKM_CAMELLIA_CBC_ENCRYPT_DATA  = 0x00000557L;
    long  CKM_CAMELLIA_CTR               = 0x00000558L;

    long  CKM_ARIA_KEY_GEN               = 0x00000560L;
    long  CKM_ARIA_ECB                   = 0x00000561L;
    long  CKM_ARIA_CBC                   = 0x00000562L;
    long  CKM_ARIA_MAC                   = 0x00000563L;
    long  CKM_ARIA_MAC_GENERAL           = 0x00000564L;
    long  CKM_ARIA_CBC_PAD               = 0x00000565L;
    long  CKM_ARIA_ECB_ENCRYPT_DATA      = 0x00000566L;
    long  CKM_ARIA_CBC_ENCRYPT_DATA      = 0x00000567L;

    long  CKM_SEED_KEY_GEN               = 0x00000650L;
    long  CKM_SEED_ECB                   = 0x00000651L;
    long  CKM_SEED_CBC                   = 0x00000652L;
    long  CKM_SEED_MAC                   = 0x00000653L;
    long  CKM_SEED_MAC_GENERAL           = 0x00000654L;
    long  CKM_SEED_CBC_PAD               = 0x00000655L;
    long  CKM_SEED_ECB_ENCRYPT_DATA      = 0x00000656L;
    long  CKM_SEED_CBC_ENCRYPT_DATA      = 0x00000657L;

    long  CKM_SKIPJACK_KEY_GEN           = 0x00001000L;
    long  CKM_SKIPJACK_ECB64             = 0x00001001L;
    long  CKM_SKIPJACK_CBC64             = 0x00001002L;
    long  CKM_SKIPJACK_OFB64             = 0x00001003L;
    long  CKM_SKIPJACK_CFB64             = 0x00001004L;
    long  CKM_SKIPJACK_CFB32             = 0x00001005L;
    long  CKM_SKIPJACK_CFB16             = 0x00001006L;
    long  CKM_SKIPJACK_CFB8              = 0x00001007L;
    long  CKM_SKIPJACK_WRAP              = 0x00001008L;
    long  CKM_SKIPJACK_PRIVATE_WRAP      = 0x00001009L;
    long  CKM_SKIPJACK_RELAYX            = 0x0000100AL;
    long  CKM_KEA_KEY_PAIR_GEN           = 0x00001010L;
    long  CKM_KEA_KEY_DERIVE             = 0x00001011L;
    long  CKM_KEA_DERIVE                 = 0x00001012L;
    long  CKM_FORTEZZA_TIMESTAMP         = 0x00001020L;
    long  CKM_BATON_KEY_GEN              = 0x00001030L;
    long  CKM_BATON_ECB128               = 0x00001031L;
    long  CKM_BATON_ECB96                = 0x00001032L;
    long  CKM_BATON_CBC128               = 0x00001033L;
    long  CKM_BATON_COUNTER              = 0x00001034L;
    long  CKM_BATON_SHUFFLE              = 0x00001035L;
    long  CKM_BATON_WRAP                 = 0x00001036L;

    long  CKM_ECDSA_KEY_PAIR_GEN /*deprecated*/
                                                             = 0x00001040L;
    long  CKM_EC_KEY_PAIR_GEN            = 0x00001040L;
    long  CKM_EC_KEY_PAIR_GEN_W_EXTRA_BITS = 0x0000140BL;

    long  CKM_ECDSA                      = 0x00001041L;
    long  CKM_ECDSA_SHA1                 = 0x00001042L;
    long  CKM_ECDSA_SHA224               = 0x00001043L;
    long  CKM_ECDSA_SHA256               = 0x00001044L;
    long  CKM_ECDSA_SHA384               = 0x00001045L;
    long  CKM_ECDSA_SHA512               = 0x00001046L;
    long  CKM_ECDSA_SHA3_224             = 0x00001047L;
    long  CKM_ECDSA_SHA3_256             = 0x00001048L;
    long  CKM_ECDSA_SHA3_384             = 0x00001049L;
    long  CKM_ECDSA_SHA3_512             = 0x0000104AL;

    long  CKM_ECDH1_DERIVE               = 0x00001050L;
    long  CKM_ECDH1_COFACTOR_DERIVE      = 0x00001051L;
    long  CKM_ECMQV_DERIVE               = 0x00001052L;

    long  CKM_ECDH_AES_KEY_WRAP          = 0x00001053L;
    long  CKM_RSA_AES_KEY_WRAP           = 0x00001054L;

    long  CKM_EC_EDWARDS_KEY_PAIR_GEN    = 0x00001055L;
    long  CKM_EC_MONTGOMERY_KEY_PAIR_GEN = 0x00001056L;
    long  CKM_EDDSA                      = 0x00001057L;

    long  CKM_JUNIPER_KEY_GEN            = 0x00001060L;
    long  CKM_JUNIPER_ECB128             = 0x00001061L;
    long  CKM_JUNIPER_CBC128             = 0x00001062L;
    long  CKM_JUNIPER_COUNTER            = 0x00001063L;
    long  CKM_JUNIPER_SHUFFLE            = 0x00001064L;
    long  CKM_JUNIPER_WRAP               = 0x00001065L;
    long  CKM_FASTHASH                   = 0x00001070L;

    long  CKM_AES_XTS                    = 0x00001071L;
    long  CKM_AES_XTS_KEY_GEN            = 0x00001072L;
    long  CKM_AES_KEY_GEN                = 0x00001080L;
    long  CKM_AES_ECB                    = 0x00001081L;
    long  CKM_AES_CBC                    = 0x00001082L;
    long  CKM_AES_MAC                    = 0x00001083L;
    long  CKM_AES_MAC_GENERAL            = 0x00001084L;
    long  CKM_AES_CBC_PAD                = 0x00001085L;
    long  CKM_AES_CTR                    = 0x00001086L;
    long  CKM_AES_GCM                    = 0x00001087L;
    long  CKM_AES_CCM                    = 0x00001088L;
    long  CKM_AES_CTS                    = 0x00001089L;
    long  CKM_AES_CMAC                   = 0x0000108AL;
    long  CKM_AES_CMAC_GENERAL           = 0x0000108BL;

    long  CKM_AES_XCBC_MAC               = 0x0000108CL;
    long  CKM_AES_XCBC_MAC_96            = 0x0000108DL;
    long  CKM_AES_GMAC                   = 0x0000108EL;

    long  CKM_BLOWFISH_KEY_GEN           = 0x00001090L;
    long  CKM_BLOWFISH_CBC               = 0x00001091L;
    long  CKM_TWOFISH_KEY_GEN            = 0x00001092L;
    long  CKM_TWOFISH_CBC                = 0x00001093L;
    long  CKM_BLOWFISH_CBC_PAD           = 0x00001094L;
    long  CKM_TWOFISH_CBC_PAD            = 0x00001095L;

    long  CKM_DES_ECB_ENCRYPT_DATA       = 0x00001100L;
    long  CKM_DES_CBC_ENCRYPT_DATA       = 0x00001101L;
    long  CKM_DES3_ECB_ENCRYPT_DATA      = 0x00001102L;
    long  CKM_DES3_CBC_ENCRYPT_DATA      = 0x00001103L;
    long  CKM_AES_ECB_ENCRYPT_DATA       = 0x00001104L;
    long  CKM_AES_CBC_ENCRYPT_DATA       = 0x00001105L;

    long  CKM_GOSTR3410_KEY_PAIR_GEN     = 0x00001200L;
    long  CKM_GOSTR3410                  = 0x00001201L;
    long  CKM_GOSTR3410_WITH_GOSTR3411   = 0x00001202L;
    long  CKM_GOSTR3410_KEY_WRAP         = 0x00001203L;
    long  CKM_GOSTR3410_DERIVE           = 0x00001204L;
    long  CKM_GOSTR3411                  = 0x00001210L;
    long  CKM_GOSTR3411_HMAC             = 0x00001211L;
    long  CKM_GOST28147_KEY_GEN          = 0x00001220L;
    long  CKM_GOST28147_ECB              = 0x00001221L;
    long  CKM_GOST28147                  = 0x00001222L;
    long  CKM_GOST28147_MAC              = 0x00001223L;
    long  CKM_GOST28147_KEY_WRAP         = 0x00001224L;

    long  CKM_CHACHA20_KEY_GEN           = 0x00001225L;
    long  CKM_CHACHA20                   = 0x00001226L;
    long  CKM_POLY1305_KEY_GEN           = 0x00001227L;
    long  CKM_POLY1305                   = 0x00001228L;

    long  CKM_DSA_PARAMETER_GEN          = 0x00002000L;
    long  CKM_DH_PKCS_PARAMETER_GEN      = 0x00002001L;
    long  CKM_X9_42_DH_PARAMETER_GEN     = 0x00002002L;
    long  CKM_DSA_PROBABLISTIC_PARAMETER_GEN = 0x00002003L;
    long  CKM_DSA_SHAWE_TAYLOR_PARAMETER_GEN = 0x00002004L;
    long  CKM_DSA_FIPS_G_GEN             = 0x00002005L;

    long  CKM_AES_OFB                    = 0x00002104L;
    long  CKM_AES_CFB64                  = 0x00002105L;
    long  CKM_AES_CFB8                   = 0x00002106L;
    long  CKM_AES_CFB128                 = 0x00002107L;
    long  CKM_AES_CFB1                   = 0x00002108L;
    long  CKM_AES_KEY_WRAP /* WAS: 0x00001090 */
                                                             = 0x00002109L;
    long  CKM_AES_KEY_WRAP_PAD /* WAS: 0x00001091 */
                                                             = 0x0000210AL;
    long  CKM_AES_KEY_WRAP_KWP           = 0x0000210BL;

    long  CKM_RSA_PKCS_TPM_1_1           = 0x00004001L;
    long  CKM_RSA_PKCS_OAEP_TPM_1_1      = 0x00004002L;

    long  CKM_SHA_1_KEY_GEN              = 0x00004003L;
    long  CKM_SHA224_KEY_GEN             = 0x00004004L;
    long  CKM_SHA256_KEY_GEN             = 0x00004005L;
    long  CKM_SHA384_KEY_GEN             = 0x00004006L;
    long  CKM_SHA512_KEY_GEN             = 0x00004007L;
    long  CKM_SHA512_224_KEY_GEN         = 0x00004008L;
    long  CKM_SHA512_256_KEY_GEN         = 0x00004009L;
    long  CKM_SHA512_T_KEY_GEN           = 0x0000400aL;
    long  CKM_NULL                       = 0x0000400bL;
    long  CKM_BLAKE2B_160                = 0x0000400cL;
    long  CKM_BLAKE2B_160_HMAC           = 0x0000400dL;
    long  CKM_BLAKE2B_160_HMAC_GENERAL   = 0x0000400eL;
    long  CKM_BLAKE2B_160_KEY_DERIVE     = 0x0000400fL;
    long  CKM_BLAKE2B_160_KEY_GEN        = 0x00004010L;
    long  CKM_BLAKE2B_256                = 0x00004011L;
    long  CKM_BLAKE2B_256_HMAC           = 0x00004012L;
    long  CKM_BLAKE2B_256_HMAC_GENERAL   = 0x00004013L;
    long  CKM_BLAKE2B_256_KEY_DERIVE     = 0x00004014L;
    long  CKM_BLAKE2B_256_KEY_GEN        = 0x00004015L;
    long  CKM_BLAKE2B_384                = 0x00004016L;
    long  CKM_BLAKE2B_384_HMAC           = 0x00004017L;
    long  CKM_BLAKE2B_384_HMAC_GENERAL   = 0x00004018L;
    long  CKM_BLAKE2B_384_KEY_DERIVE     = 0x00004019L;
    long  CKM_BLAKE2B_384_KEY_GEN        = 0x0000401aL;
    long  CKM_BLAKE2B_512                = 0x0000401bL;
    long  CKM_BLAKE2B_512_HMAC           = 0x0000401cL;
    long  CKM_BLAKE2B_512_HMAC_GENERAL   = 0x0000401dL;
    long  CKM_BLAKE2B_512_KEY_DERIVE     = 0x0000401eL;
    long  CKM_BLAKE2B_512_KEY_GEN        = 0x0000401fL;
    long  CKM_SALSA20                    = 0x00004020L;
    long  CKM_CHACHA20_POLY1305          = 0x00004021L;
    long  CKM_SALSA20_POLY1305           = 0x00004022L;
    long  CKM_X3DH_INITIALIZE            = 0x00004023L;
    long  CKM_X3DH_RESPOND               = 0x00004024L;
    long  CKM_X2RATCHET_INITIALIZE       = 0x00004025L;
    long  CKM_X2RATCHET_RESPOND          = 0x00004026L;
    long  CKM_X2RATCHET_ENCRYPT          = 0x00004027L;
    long  CKM_X2RATCHET_DECRYPT          = 0x00004028L;
    long  CKM_XEDDSA                     = 0x00004029L;
    long  CKM_HKDF_DERIVE                = 0x0000402aL;
    long  CKM_HKDF_DATA                  = 0x0000402bL;
    long  CKM_HKDF_KEY_GEN               = 0x0000402cL;
    long  CKM_SALSA20_KEY_GEN            = 0x0000402dL;

    long  CKM_SP800_108_COUNTER_KDF      = 0x000003acL;
    long  CKM_SP800_108_FEEDBACK_KDF     = 0x000003adL;
    long  CKM_SP800_108_DOUBLE_PIPELINE_KDF = 0x000003aeL;

    long  CKM_VENDOR_DEFINED             = 0x80000000L;

    // NSS private
    long  CKM_NSS_TLS_PRF_GENERAL        = 0x80000373L;

    // internal ids for our pseudo mechanisms SecureRandom and KeyStore
    long  PCKM_SECURERANDOM              = 0x7FFFFF20L;
    long  PCKM_KEYSTORE                  = 0x7FFFFF21L;

    /* The flags specify whether or not a mechanism can be used for a
     * particular task */
    long  CKF_HW                 = 0x00000001L;
    long  CKF_MESSAGE_ENCRYPT    = 0x00000002L;
    long  CKF_MESSAGE_DECRYPT    = 0x00000004L;
    long  CKF_MESSAGE_SIGN       = 0x00000008L;
    long  CKF_MESSAGE_VERIFY     = 0x00000010L;
    long  CKF_MULTI_MESSAGE      = 0x00000020L;
    long  CKF_FIND_OBJECTS       = 0x00000040L;

    long  CKF_ENCRYPT            = 0x00000100L;
    long  CKF_DECRYPT            = 0x00000200L;
    long  CKF_DIGEST             = 0x00000400L;
    long  CKF_SIGN               = 0x00000800L;
    long  CKF_SIGN_RECOVER       = 0x00001000L;
    long  CKF_VERIFY             = 0x00002000L;
    long  CKF_VERIFY_RECOVER     = 0x00004000L;
    long  CKF_GENERATE           = 0x00008000L;
    long  CKF_GENERATE_KEY_PAIR  = 0x00010000L;
    long  CKF_WRAP               = 0x00020000L;
    long  CKF_UNWRAP             = 0x00040000L;
    long  CKF_DERIVE             = 0x00080000L;

    /* Describe a token's EC capabilities not available in mechanism
     * information.
     */
    long  CKF_EC_F_P             = 0x00100000L;
    long  CKF_EC_F_2M            = 0x00200000L;
    long  CKF_EC_ECPARAMETERS    = 0x00400000L;
    long  CKF_EC_OID             = 0x00400000L;
    long  CKF_EC_NAMEDCURVE /*deprecated since 3.00*/
                                                     = CKF_EC_OID;
    long  CKF_EC_UNCOMPRESS      = 0x01000000L;
    long  CKF_EC_COMPRESS        = 0x02000000L;
    long  CKF_EC_CURVENAME       = 0x04000000L;

    long  CKF_EXTENSION          = 0x80000000L;

    /* Identifies the return value of a Cryptoki function */
    // These CKR_xxx constants are stored/defined in PKCS11Exception class

    /* Uncomment when actually used
    public static final long  CKF_END_OF_MESSAGE      = 0x00000001L;
    public static final long  CKF_INTERFACE_FORK_SAFE = 0x00000001L;
    */

    /* flags: bit flags that provide capabilities of the slot
     *        Bit Flag = Mask
     */
    long  CKF_LIBRARY_CANT_CREATE_OS_THREADS = 0x00000001L;
    long  CKF_OS_LOCKING_OK                  = 0x00000002L;

    /* CKF_DONT_BLOCK is for the function C_WaitForSlotEvent */
    long  CKF_DONT_BLOCK =    1L;

    /* The following MGFs are defined */
    long  CKG_MGF1_SHA1       = 0x00000001L;
    long  CKG_MGF1_SHA256     = 0x00000002L;
    long  CKG_MGF1_SHA384     = 0x00000003L;
    long  CKG_MGF1_SHA512     = 0x00000004L;
    long  CKG_MGF1_SHA224     = 0x00000005L;
    long  CKG_MGF1_SHA3_224   = 0x00000006L;
    long  CKG_MGF1_SHA3_256   = 0x00000007L;
    long  CKG_MGF1_SHA3_384   = 0x00000008L;
    long  CKG_MGF1_SHA3_512   = 0x00000009L;

    /* The following encoding parameter sources are defined */
    long  CKZ_DATA_SPECIFIED   = 0x00000001L;

    // the following EC Key Derivation Functions are defined
    long  CKD_NULL                 = 0x00000001L;
    long  CKD_SHA1_KDF             = 0x00000002L;

    /* Uncomment when actually used
    // the following X9.42 Diffie-Hellman Key Derivation Functions are defined
    public static final long  CKD_SHA1_KDF_ASN1        = 0x00000003L;
    public static final long  CKD_SHA1_KDF_CONCATENATE = 0x00000004L;
    public static final long  CKD_SHA224_KDF           = 0x00000005L;
    public static final long  CKD_SHA256_KDF           = 0x00000006L;
    public static final long  CKD_SHA384_KDF           = 0x00000007L;
    public static final long  CKD_SHA512_KDF           = 0x00000008L;
    public static final long  CKD_CPDIVERSIFY_KDF      = 0x00000009L;
    public static final long  CKD_SHA3_224_KDF         = 0x0000000AL;
    public static final long  CKD_SHA3_256_KDF         = 0x0000000BL;
    public static final long  CKD_SHA3_384_KDF         = 0x0000000CL;
    public static final long  CKD_SHA3_512_KDF         = 0x0000000DL;
    public static final long  CKD_SHA1_KDF_SP800       = 0x0000000EL;
    public static final long  CKD_SHA224_KDF_SP800     = 0x0000000FL;
    public static final long  CKD_SHA256_KDF_SP800     = 0x00000010L;
    public static final long  CKD_SHA384_KDF_SP800     = 0x00000011L;
    public static final long  CKD_SHA512_KDF_SP800     = 0x00000012L;
    public static final long  CKD_SHA3_224_KDF_SP800   = 0x00000013L;
    public static final long  CKD_SHA3_256_KDF_SP800   = 0x00000014L;
    public static final long  CKD_SHA3_384_KDF_SP800   = 0x00000015L;
    public static final long  CKD_SHA3_512_KDF_SP800   = 0x00000016L;
    public static final long  CKD_BLAKE2B_160_KDF      = 0x00000017L;
    public static final long  CKD_BLAKE2B_256_KDF      = 0x00000018L;
    public static final long  CKD_BLAKE2B_384_KDF      = 0x00000019L;
    public static final long  CKD_BLAKE2B_512_KDF      = 0x0000001aL;

    public static final long  CKP_PKCS5_PBKD2_HMAC_SHA1        = 0x00000001L;
    public static final long  CKP_PKCS5_PBKD2_HMAC_GOSTR3411   = 0x00000002L;
    public static final long  CKP_PKCS5_PBKD2_HMAC_SHA224      = 0x00000003L;
    public static final long  CKP_PKCS5_PBKD2_HMAC_SHA256      = 0x00000004L;
    public static final long  CKP_PKCS5_PBKD2_HMAC_SHA384      = 0x00000005L;
    public static final long  CKP_PKCS5_PBKD2_HMAC_SHA512      = 0x00000006L;
    public static final long  CKP_PKCS5_PBKD2_HMAC_SHA512_224  = 0x00000007L;
    public static final long  CKP_PKCS5_PBKD2_HMAC_SHA512_256  = 0x00000008L;

    public static final long  CKZ_SALT_SPECIFIED      = 0x00000001L;

    public static final long  CK_OTP_VALUE            = 0x00000000L;
    public static final long  CK_OTP_PIN              = 0x00000001L;
    public static final long  CK_OTP_CHALLENGE        = 0x00000002L;
    public static final long  CK_OTP_TIME             = 0x00000003L;
    public static final long  CK_OTP_COUNTER          = 0x00000004L;
    public static final long  CK_OTP_FLAGS            = 0x00000005L;
    public static final long  CK_OTP_OUTPUT_LENGTH    = 0x00000006L;
    public static final long  CK_OTP_OUTPUT_FORMAT    = 0x00000007L;

    public static final long  CKF_NEXT_OTP            = 0x00000001L;
    public static final long  CKF_EXCLUDE_TIME        = 0x00000002L;
    public static final long  CKF_EXCLUDE_COUNTER     = 0x00000004L;
    public static final long  CKF_EXCLUDE_CHALLENGE   = 0x00000008L;
    public static final long  CKF_EXCLUDE_PIN         = 0x00000010L;
    public static final long  CKF_USER_FRIENDLY_OTP   = 0x00000020L;

    public static final long  CKG_NO_GENERATE      = 0x00000000L;
    public static final long  CKG_GENERATE         = 0x00000001L;
    public static final long  CKG_GENERATE_COUNTER = 0x00000002L;
    public static final long  CKG_GENERATE_RANDOM  = 0x00000003L;

    public static final long  CK_SP800_108_ITERATION_VARIABLE = 0x00000001L;
    public static final long  CK_SP800_108_OPTIONAL_COUNTER   = 0x00000002L;
    public static final long  CK_SP800_108_DKM_LENGTH         = 0x00000003L;
    public static final long  CK_SP800_108_BYTE_ARRAY         = 0x00000004L;
    public static final long  CK_SP800_108_DKM_LENGTH_SUM_OF_KEYS
                                                              = 0x00000001L;
    public static final long  CK_SP800_108_DKM_LENGTH_SUM_OF_SEGMENTS
                                                              = 0x00000002L;

    public static final long  CKF_HKDF_SALT_NULL   = 0x00000001L;
    public static final long  CKF_HKDF_SALT_DATA   = 0x00000002L;
    public static final long  CKF_HKDF_SALT_KEY    = 0x00000004L;
    */

    // private NSS attribute (for DSA and DH private keys)
    long  CKA_NETSCAPE_DB         = 0xD5A0DB00L;

    // base number of NSS private attributes
    long  CKA_NETSCAPE_BASE /*0x80000000L + 0x4E534350L*/
                                                      = 0xCE534350L;

    // object type for NSS trust
    long  CKO_NETSCAPE_TRUST      = 0xCE534353L;

    // base number for NSS trust attributes
    long  CKA_NETSCAPE_TRUST_BASE = 0xCE536350L;

    // attributes for NSS trust
    long  CKA_NETSCAPE_TRUST_SERVER_AUTH      = 0xCE536358L;
    long  CKA_NETSCAPE_TRUST_CLIENT_AUTH      = 0xCE536359L;
    long  CKA_NETSCAPE_TRUST_CODE_SIGNING     = 0xCE53635AL;
    long  CKA_NETSCAPE_TRUST_EMAIL_PROTECTION = 0xCE53635BL;
    long  CKA_NETSCAPE_CERT_SHA1_HASH         = 0xCE5363B4L;
    long  CKA_NETSCAPE_CERT_MD5_HASH          = 0xCE5363B5L;

    // trust values for each of the NSS trust attributes
    long  CKT_NETSCAPE_TRUSTED           = 0xCE534351L;
    long  CKT_NETSCAPE_TRUSTED_DELEGATOR = 0xCE534352L;
    long  CKT_NETSCAPE_UNTRUSTED         = 0xCE534353L;
    long  CKT_NETSCAPE_MUST_VERIFY       = 0xCE534354L;
    long  CKT_NETSCAPE_TRUST_UNKNOWN /* default */
                                                             = 0xCE534355L;
    long  CKT_NETSCAPE_VALID             = 0xCE53435AL;
    long  CKT_NETSCAPE_VALID_DELEGATOR   = 0xCE53435BL;
}
