/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 02/nov/2013
 * Copyright 2017 by Sober Lemur S.a.s. (info@pdfsam.org).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.pdfsam.module;

import java.util.List;

/**
 * Service providing modules usage related functionalities
 * 
 * @author Andrea Vacondio
 * 
 */
public interface UsageService {

    /**
     * Increments the usage of the given module
     * 
     * @param moduleId
     */
    void incrementUsageFor(String moduleId);

    /**
     * @return a sorted list with the most used modules
     */
    List<Module> getMostUsed();

    /**
     * @return a sorted list with the most recently modules
     */
    List<Module> getMostRecentlyUsed();

    /**
     * Clear usage statistics
     */
    void clear();

    /**
     * @return number of total executed tasks
     */
    long getTotalUsage();
}
