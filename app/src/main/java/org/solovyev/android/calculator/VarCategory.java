/*
 * Copyright 2013 serso aka se.solovyev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Contact details
 *
 * Email: se.solovyev@gmail.com
 * Site:  http://se.solovyev.org
 */

package org.solovyev.android.calculator;

import android.support.annotation.StringRes;
import jscl.math.function.IConstant;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum VarCategory implements Category {

    system(100, R.string.c_var_system) {
        @Override
        public boolean isInCategory(@Nonnull IConstant var) {
            return var.isSystem();
        }
    },

    my(0, R.string.c_var_my) {
        @Override
        public boolean isInCategory(@Nonnull IConstant var) {
            return !var.isSystem();
        }
    };

    private final int tabOrder;
    @StringRes
    private final int title;

    VarCategory(int tabOrder, @StringRes int title) {
        this.tabOrder = tabOrder;
        this.title = title;
    }

    @Nonnull
    public static List<VarCategory> getCategoriesByTabOrder() {
        final List<VarCategory> result = Arrays.asList(VarCategory.values());

        java.util.Collections.sort(result, new Comparator<VarCategory>() {
            @Override
            public int compare(VarCategory category, VarCategory category1) {
                return category.tabOrder - category1.tabOrder;
            }
        });

        return result;
    }

    public abstract boolean isInCategory(@Nonnull IConstant var);


    @Override
    public int title() {
        return title;
    }
}
