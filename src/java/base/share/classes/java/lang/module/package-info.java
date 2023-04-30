/java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.lang.module/

/java.base.share.classes.java.lang.modulejava.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module Classes to support module descriptors and creating configurations of modules
 java.base.share.classes.java.lang.module by means of resolution and service binding.
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> Unless otherwise noted, passing a {@code null} argument to a constructor
 java.base.share.classes.java.lang.module or method of any class or interface in this package will cause a {@link
 java.base.share.classes.java.lang.module java.lang.NullPointerException NullPointerException} to be thrown. Additionally,
 java.base.share.classes.java.lang.module invoking a method with an array or collection containing a {@code null} element
 java.base.share.classes.java.lang.module will cause a {@code NullPointerException}, unless otherwise specified. </p>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <h2><a id="resolution"></a>{@index "Module Resolution"}</h2>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> Resolution is the process of computing how modules depend on each other.
 java.base.share.classes.java.lang.module The process occurs at compile time and run time. </p>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> Resolution is a two-step process. The first step recursively enumerates
 java.base.share.classes.java.lang.module the 'requires' directives of a set of root modules. If all the enumerated
 java.base.share.classes.java.lang.module modules are observable, then the second step computes their readability graph.
 java.base.share.classes.java.lang.module The readability graph embodies how modules depend on each other, which in
 java.base.share.classes.java.lang.module turn controls access across module boundaries. </p>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <h3> Step 1: Recursive enumeration </h3>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> Recursive enumeration takes a set of module names, looks up each of their
 java.base.share.classes.java.lang.module module declarations, and for each module declaration, recursively enumerates:
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <ul>
 java.base.share.classes.java.lang.module   <li> <p> the module names given by the 'requires' directives with the
 java.base.share.classes.java.lang.module   'transitive' modifier, and </p></li>
 java.base.share.classes.java.lang.module   <li> <p> at the discretion of the host system, the module names given by
 java.base.share.classes.java.lang.module   the 'requires' directives without the 'transitive' modifier. </p></li>
 java.base.share.classes.java.lang.module </ul>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> Module declarations are looked up in a set of observable modules. The set
 java.base.share.classes.java.lang.module of observable modules is determined in an implementation specific manner. The
 java.base.share.classes.java.lang.module set of observable modules may include modules with explicit declarations
 java.base.share.classes.java.lang.module (that is, with a {@code module-info.java} source file or {@code module-info.class}
 java.base.share.classes.java.lang.module file) and modules with implicit declarations (that is,
 java.base.share.classes.java.lang.module <a href="ModuleFinder.html#automatic-modules">automatic modules</a>).
 java.base.share.classes.java.lang.module Because an automatic module has no explicit module declaration, it has no
 java.base.share.classes.java.lang.module 'requires' directives of its own, although its name may be given by a
 java.base.share.classes.java.lang.module 'requires' directive of an explicit module declaration. </p>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> The set of root modules, whose names are the initial input to this
 java.base.share.classes.java.lang.module algorithm, is determined in an implementation specific manner. The set of
 java.base.share.classes.java.lang.module root modules may include automatic modules. </p>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> If at least one automatic module is enumerated by this algorithm, then
 java.base.share.classes.java.lang.module every observable automatic module must be enumerated, regardless of whether
 java.base.share.classes.java.lang.module any of their names are given by 'requires' directives of explicit module
 java.base.share.classes.java.lang.module declarations. </p>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> If any of the following conditions occur, then resolution fails:
 java.base.share.classes.java.lang.module <ul>
 java.base.share.classes.java.lang.module   <li><p> Any root module is not observable. </p></li>
 java.base.share.classes.java.lang.module   <li><p> Any module whose name is given by a 'requires' directive with the
 java.base.share.classes.java.lang.module   'transitive' modifier is not observable. </p></li>
 java.base.share.classes.java.lang.module   <li><p> At the discretion of the host system, any module whose name is given
 java.base.share.classes.java.lang.module   by a 'requires' directive without the 'transitive' modifier is not
 java.base.share.classes.java.lang.module   observable. </p></li>
 java.base.share.classes.java.lang.module   <li><p> The algorithm in this step enumerates the same module name twice. This
 java.base.share.classes.java.lang.module   indicates a cycle in the 'requires' directives, disregarding any 'transitive'
 java.base.share.classes.java.lang.module   modifiers. </p></li>
 java.base.share.classes.java.lang.module </ul>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> Otherwise, resolution proceeds to step 2. </p>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <h3> Step 2: Computing the readability graph </h3>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> A 'requires' directive (irrespective of 'transitive') expresses that
 java.base.share.classes.java.lang.module one module depends on some other module. The effect of the 'transitive'
 java.base.share.classes.java.lang.module modifier is to cause additional modules to also depend on the other module.
 java.base.share.classes.java.lang.module If module M 'requires transitive N', then not only does M depend on N, but
 java.base.share.classes.java.lang.module any module that depends on M also depends on N. This allows M to be
 java.base.share.classes.java.lang.module refactored so that some or all of its content can be moved to a new module N
 java.base.share.classes.java.lang.module without breaking modules that have a 'requires M' directive. </p>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> Module dependencies are represented by the readability graph. The
 java.base.share.classes.java.lang.module readability graph is a directed graph whose vertices are the modules
 java.base.share.classes.java.lang.module enumerated in step 1 and whose edges represent readability between pairs of
 java.base.share.classes.java.lang.module modules. The edges are specified as follows:
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> First, readability is determined by the 'requires' directives of the
 java.base.share.classes.java.lang.module enumerated modules, disregarding any 'transitive' modifiers:
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <ul>
 java.base.share.classes.java.lang.module   <li><p> For each enumerated module A that 'requires' B: A "reads" B. </p></li>
 java.base.share.classes.java.lang.module   <li><p> For each enumerated module X that is automatic: X "reads" every
 java.base.share.classes.java.lang.module   other enumerated module (it is "as if" an automatic module has 'requires'
 java.base.share.classes.java.lang.module   directives for every other enumerated module). </p></li>
 java.base.share.classes.java.lang.module </ul>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> Second, readability is augmented to account for 'transitive' modifiers:
 java.base.share.classes.java.lang.module <ul>
 java.base.share.classes.java.lang.module   <li> <p> For each enumerated module A that "reads" B: </p>
 java.base.share.classes.java.lang.module     <ul>
 java.base.share.classes.java.lang.module     <li><p> If B 'requires transitive' C, then A "reads" C as well as B. This
 java.base.share.classes.java.lang.module     augmentation is recursive: since A "reads" C, if C 'requires transitive'
 java.base.share.classes.java.lang.module     D, then A "reads" D as well as C and B. </p></li>
 java.base.share.classes.java.lang.module     <li><p> If B is an automatic module, then A "reads" every other enumerated
 java.base.share.classes.java.lang.module     automatic module. (It is "as if" an automatic module has 'requires transitive'
 java.base.share.classes.java.lang.module     directives for every other enumerated automatic module).</p> </li>
 java.base.share.classes.java.lang.module     </ul>
 java.base.share.classes.java.lang.module   </li>
 java.base.share.classes.java.lang.module </ul>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> Finally, every module "reads" itself. </p>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> If any of the following conditions occur in the readability graph, then
 java.base.share.classes.java.lang.module resolution fails:
 java.base.share.classes.java.lang.module <ul>
 java.base.share.classes.java.lang.module   <li><p> A module "reads" two or more modules with the same name. This includes
 java.base.share.classes.java.lang.module   the case where a module "reads" another with the same name as itself. </p></li>
 java.base.share.classes.java.lang.module   <li><p> Two or more modules export a package with the same name to a module
 java.base.share.classes.java.lang.module   that "reads" both. This includes the case where a module M containing package
 java.base.share.classes.java.lang.module   p "reads" another module that exports p to M. </p></li>
 java.base.share.classes.java.lang.module   <li><p> A module M declares that it 'uses p.S' or 'provides p.S with ...' but
 java.base.share.classes.java.lang.module   package p is neither in module M nor exported to M by any module that M
 java.base.share.classes.java.lang.module   "reads". </p></li>
 java.base.share.classes.java.lang.module </ul>
 java.base.share.classes.java.lang.module <p> Otherwise, resolution succeeds, and the result of resolution is the
 java.base.share.classes.java.lang.module readability graph.
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <h3><a id="root-modules"></a> Root modules </h3>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> The set of root modules at compile-time is usually the set of modules
 java.base.share.classes.java.lang.module being compiled. At run-time, the set of root modules is usually the
 java.base.share.classes.java.lang.module application module specified to the 'java' launcher. When compiling code in
 java.base.share.classes.java.lang.module the unnamed module, or at run-time when the main application class is loaded
 java.base.share.classes.java.lang.module from the class path, then the default set of root modules is implementation
 java.base.share.classes.java.lang.module specific. In the JDK the default set of root modules contains every module
 java.base.share.classes.java.lang.module that is observable on the upgrade module path or among the system modules,
 java.base.share.classes.java.lang.module and that exports at least one package without qualification. </p>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <h3> Observable modules </h3>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> The set of observable modules at both compile-time and run-time is
 java.base.share.classes.java.lang.module determined by searching several different paths, and also by searching
 java.base.share.classes.java.lang.module the compiled modules built in to the environment. The search order is as
 java.base.share.classes.java.lang.module follows: </p>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <ol>
 java.base.share.classes.java.lang.module   <li><p> At compile time only, the compilation module path. This path
 java.base.share.classes.java.lang.module   contains module definitions in source form.  </p></li>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module   <li><p> The upgrade module path. This path contains compiled definitions of
 java.base.share.classes.java.lang.module   modules that will be observed in preference to the compiled definitions of
 java.base.share.classes.java.lang.module   any <i>upgradeable modules</i> that are present in (3) and (4). See the Java
 java.base.share.classes.java.lang.module   SE Platform for the designation of which standard modules are upgradeable.
 java.base.share.classes.java.lang.module   </p></li>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module   <li><p> The system modules, which are the compiled definitions built in to
 java.base.share.classes.java.lang.module   the environment. </p></li>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module   <li><p> The application module path. This path contains compiled definitions
 java.base.share.classes.java.lang.module   of library and application modules. </p></li>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module </ol>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <h3> 'requires' directives with 'static' modifier </h3>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> 'requires' directives that have the 'static' modifier express an optional
 java.base.share.classes.java.lang.module dependence at run time. If a module declares that it 'requires static M' then
 java.base.share.classes.java.lang.module resolution does not search the observable modules for M to satisfy the dependency.
 java.base.share.classes.java.lang.module However, if M is recursively enumerated at step 1 then all modules that are
 java.base.share.classes.java.lang.module enumerated and `requires static M` will read M. </p>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <h3> Completeness </h3>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> Resolution may be partial at compile-time in that the complete transitive
 java.base.share.classes.java.lang.module closure may not be required to compile a set of modules. Minimally, the
 java.base.share.classes.java.lang.module readability graph that is constructed and validated at compile-time includes
 java.base.share.classes.java.lang.module the modules being compiled, their direct dependences, and all implicitly
 java.base.share.classes.java.lang.module declared dependences (requires transitive). </p>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module <p> At run-time, resolution is an additive process. The recursive enumeration
 java.base.share.classes.java.lang.module at step 1 may be relative to previous resolutions so that a root module,
 java.base.share.classes.java.lang.module or a module named in a 'requires' directive, is not enumerated when it was
 java.base.share.classes.java.lang.module enumerated by a previous (or parent) resolution. The readability graph that
 java.base.share.classes.java.lang.module is the result of resolution may therefore have a vertex for a module enumerated
 java.base.share.classes.java.lang.module in step 1 but with an edge to represent that the module reads a module that
 java.base.share.classes.java.lang.module was enumerated by previous (or parent) resolution. </p>
 java.base.share.classes.java.lang.module
 java.base.share.classes.java.lang.module @since 9
 java.base.share.classes.java.lang.module/

package java.lang.module;
